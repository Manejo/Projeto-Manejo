package sdds.react.nativo.Initial

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import sdds.react.nativo.R
import sdds.react.nativo.Trilha.TrilhaList

class SplashAct : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Handler().postDelayed(Runnable {
            openInitialScreen()
        }, 2000)
    }

    fun openInitialScreen(){
        startActivity(Intent(this, PrincipalAct::class.java))
    }
}
