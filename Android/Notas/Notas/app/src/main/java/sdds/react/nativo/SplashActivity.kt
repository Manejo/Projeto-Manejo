package sdds.react.nativo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import sdds.react.nativo.initial.InitialScreen
import sdds.react.nativo.initial.InitialScreenNav

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Handler().postDelayed(Runnable {
            openInitialScreen()
        }, 2000)
    }

    fun openInitialScreen(){
        startActivity(Intent(this, InitialScreenNav::class.java))
    }
}
