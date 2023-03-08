package ke.co.osl.utcollectorapp

import android.Manifest
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.ProgressBar
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity

class LandingPage: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_landing)

        val progress = findViewById<ProgressBar>(R.id.progress)

        val timer = object: CountDownTimer(5000, 1) {
            override fun onTick(millisUntilFinished: Long) {
                progress.progress = (5000 - (millisUntilFinished).toInt())
            }

            override fun onFinish() {
                startActivity(Intent(this@LandingPage,LoginPageMobileAlert::class.java))
                finish()
            }
        }
        timer.start()
    }
}


