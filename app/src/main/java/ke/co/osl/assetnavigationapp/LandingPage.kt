package ke.co.osl.assetnavigationapp

import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.ProgressBar
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
                startActivity(Intent(this@LandingPage,LoginPage::class.java))
                finish()
            }
        }
        timer.start()
    }
}


