package ke.co.osl.assetnavigationapp

import android.Manifest
import android.app.Dialog
import android.app.ProgressDialog
import android.content.Intent
import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.location.Location
import android.net.http.SslError
import android.os.Build
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.webkit.JavascriptInterface
import android.webkit.SslErrorHandler
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.*
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.app.ActivityCompat
import com.auth0.android.jwt.JWT
import com.google.android.gms.location.*
import ke.co.osl.assetnavigationapp.api.ApiInterface
import ke.co.osl.assetnavigationapp.models.CustomerDetailsBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Directions : AppCompatActivity() {
    lateinit var dialog: Dialog
    lateinit var toolbar: Toolbar
    lateinit var webView: WebView
    lateinit var mFusedLocationClient: FusedLocationProviderClient
    private lateinit var locationRequest: LocationRequest
    private lateinit var locationCallback: LocationCallback
    lateinit var accuracy: TextView
    lateinit var coords: TextView
    lateinit var back: ImageView
    lateinit var preferences: SharedPreferences
    lateinit var editor: SharedPreferences.Editor

    var lat: Double = 0.0
    var lng: Double = 0.0
    var acc: Float = 0f

    val ip_URL = "http://102.222.147.190/api/homepage/"

    object AndroidJSInterface {
        @JavascriptInterface
        fun onClicked() {
            Log.d("HelpButton", "Help button clicked")
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_directions)

        //This section comprises of the navigation tools
        toolbar = findViewById(R.id.appbar)
        setSupportActionBar(toolbar)

        accuracy = findViewById(R.id.accuracy)
        coords = findViewById(R.id.coords)

        dialog = Dialog(this)
        dialog.setCancelable(true)
        dialog.setCanceledOnTouchOutside(true)
        dialog.setContentView(R.layout.customer_meters_dialog)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            requestLocationPermission()
        }

        back = findViewById(R.id.back)

        preferences = this.getSharedPreferences("ut_manager", MODE_PRIVATE)
        editor = preferences.edit()

        //Icons displayed on the Home Page
        val myLocation = findViewById<ImageView>(R.id.location)
        val refresh = findViewById<ImageView>(R.id.refresh)
        val jwt = JWT(preferences.getString("token", "")!!)

        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this)

        getLocationUpdates()

        myLocation.setOnClickListener {
            if (lat !== 0.0 && lng !== 0.0) {
                val txt = "Accuracy: " + acc.toString() + " m"
                accuracy.text = txt
                val txt1 = "Lat: " + lat.toString() + " Lng: " + lng.toString()
                coords.text = txt1
                adjustMarker(lng, lat)
                getLocationUpdates()
            } else {
                getLocationUpdates()
                Toast.makeText(this, "Location not acquired! Please wait", Toast.LENGTH_SHORT)
                    .show()
            }
        }

        val progDailog = ProgressDialog.show(this, "Loading", "Please wait...", true);
        progDailog.setCancelable(false);
        webView = findViewById<WebView>(R.id.webview)
        webView.webViewClient = WebViewClient()

        val webSettings = webView.settings
        webSettings.javaScriptEnabled = true
        webView.addJavascriptInterface(AndroidJSInterface, "Android")

        webView.webViewClient = object : WebViewClient() {
            override fun onReceivedSslError(
                view: WebView?,
                handler: SslErrorHandler?,
                error: SslError?
            ) {
                showSSLErrorDialog(handler)
            }

            override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
                progDailog.show()
                view.loadUrl(url)
                return true
            }

            override fun onPageFinished(view: WebView, url: String) {
                progDailog.dismiss()
                if (ActivityCompat.checkSelfPermission(
                        this@Directions,
                        Manifest.permission.ACCESS_FINE_LOCATION
                    )
                    != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                        this@Directions, Manifest.permission.ACCESS_COARSE_LOCATION
                    ) != PackageManager.PERMISSION_GRANTED
                ) {
                    requestLocationPermission()
                }
                try {
                    mFusedLocationClient.lastLocation.addOnSuccessListener { location: Location? ->
                        if (location != null) {
                            val txt = "Accuracy: " + location?.accuracy.toString() + " m"
                            accuracy.text = txt
                            val txt1 =
                                "Lat: " + location?.latitude?.toString() + " Lng: " + location?.longitude?.toString()
                            coords.text = txt1
                            System.out.println(location)
                            adjustMarker(location?.longitude!!, location?.latitude!!)
                            lat = location!!.latitude
                            lng = location!!.longitude
                            acc = location!!.accuracy
                        }
                    }
                } catch (ex: IllegalStateException) {

                }
            }
        }

        webView.loadUrl(ip_URL)

        //Refresh Map
        refresh.setOnClickListener {
            refreshMap()
            getLocationUpdates()
        }

        //Back to HomePage
        back.setOnClickListener {
            startActivity(Intent(this@Directions, HomePage::class.java))
        }


        searchCustomerMeter()
    }

    private fun searchCustomerMeter() {
        val search = findViewById<ImageView>(R.id.search)
        val searchtext = findViewById<TextView>(R.id.searchtext)
        val progress = findViewById<ProgressBar>(R.id.progress)
        val error = findViewById<TextView>(R.id.error)

        search.setOnClickListener {

            if (TextUtils.isEmpty(searchtext.text.toString())) {
                error.text = "Enter account number here to search!"
                return@setOnClickListener
            }

            val apiInterface = ApiInterface.create().searchCustomerDetails(searchtext.text.toString())

            apiInterface.enqueue( object : Callback<List<CustomerDetailsBody>> {
                override fun onResponse(call: Call<List<CustomerDetailsBody>>, response: Response<List<CustomerDetailsBody>>?) {
                    if (response?.body()?.size!! > 0) {
                        System.out.println(response?.body())
                        progress.visibility = View.GONE
                        showCustomersDetailsDialog(dialog, response?.body()?.get(0)!!)
                    } else {
                        error.text = "Account Number not found!"
                    }
                }

                override fun onFailure(call: Call<List<CustomerDetailsBody>>, t: Throwable) {
                    progress.visibility = View.GONE
                    System.out.println(t)
                    error.text = "Connection to server failed!"
                }
            })
        }

    }

    private fun showCustomersDetailsDialog(d: Dialog, get: CustomerDetailsBody) {
        val customername = d.findViewById<TextView>(R.id.name)
        val accountno = d.findViewById<TextView>(R.id.account_number)
        val meterno = d.findViewById<TextView>(R.id.meter_number)
        val directions = d.findViewById<TextView>(R.id.directions)

        customername.setText("C/Name: " + get.Name)
        accountno.setText("Account/No: " + get.AccountNo)
        meterno.setText( "Meter/No: " + get.MeterNo)

        directions.setOnClickListener {
            d.hide()
            Toast.makeText(this,"This part is under development", Toast.LENGTH_SHORT)
        }

        d.getWindow()?.setBackgroundDrawableResource(R.drawable.background_transparent);
        d.setCancelable(true);
        val window: Window = d.getWindow()!!
        window.setLayout(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.MATCH_PARENT
        )
        d.show()
    }


    private fun showSSLErrorDialog(handler: SslErrorHandler?) {
        handler?.proceed()
    }

    private fun refreshMap() {
        webView.loadUrl(ip_URL)
    }

    fun adjustMarker(x: Double, y: Double) {
        webView.loadUrl(
            "javascript:(adjustMarker($x,$y))"
        )
    }

    fun getLocationUpdates() {
        locationRequest = LocationRequest()
        locationRequest.interval = 50
        locationRequest.fastestInterval = 50
        locationRequest.smallestDisplacement = 0.01f // 170 m = 0.1 mile
        locationRequest.priority = LocationRequest.PRIORITY_HIGH_ACCURACY //set according to your app function

//        try {
        locationCallback = object : LocationCallback() {
            override fun onLocationResult(locationResult: LocationResult?) {
                locationResult ?: return

                if (locationResult.locations.isNotEmpty()) {
                    System.out.println(locationResult.lastLocation)
                    val lc = locationResult.lastLocation
                    val txt = "Accuracy: " + lc?.accuracy.toString() + " m"
                    accuracy.text = txt
                    val txt1 =
                        "Lat: " + lc?.latitude.toString() + " Lng: " + lc?.longitude!!.toString()
                    coords.text = txt1
                    adjustMarker(lc?.longitude!!, lc?.latitude!!)
                    lat = lc!!.latitude
                    lng = lc!!.longitude
                    acc = lc!!.accuracy
                }

            }
        }
//        } catch (ex: IllegalStateException) {
//        }
    }

    //start location updates
    fun startLocationUpdates() {
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                requestLocationPermission()
            } else Toast.makeText(this, "Location not acquired", Toast.LENGTH_LONG).show()
        } else mFusedLocationClient.requestLocationUpdates(locationRequest, locationCallback, null)
    }

    // stop location updates
    fun stopLocationUpdates() {
        mFusedLocationClient.removeLocationUpdates(locationCallback)
    }

    //request location permission
    fun requestLocationPermission() {
        val locationPermissionRequest = registerForActivityResult(
            ActivityResultContracts.RequestMultiplePermissions()
        ) { permissions ->
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                when {
                    permissions.getOrDefault(Manifest.permission.ACCESS_FINE_LOCATION, false) -> {
                    }
                    permissions.getOrDefault(Manifest.permission.ACCESS_COARSE_LOCATION, false) -> {
                    }
                    else -> {
                    }
                }
            }
        }
        // 7o7gi9h9
        locationPermissionRequest.launch(
            arrayOf(
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION
            )
        )
    }

    override fun onResume() {
        super.onResume()
        startLocationUpdates()
        webView.loadUrl(ip_URL)
    }

    override fun onPause() {
        super.onPause()
        stopLocationUpdates()
    }

    override fun onDestroy() {
        super.onDestroy()
        stopLocationUpdates()
    }

    override fun onStart() {
        super.onStart()
        startLocationUpdates()
    }

    override fun onStop() {
        super.onStop()
        stopLocationUpdates()
    }
}
