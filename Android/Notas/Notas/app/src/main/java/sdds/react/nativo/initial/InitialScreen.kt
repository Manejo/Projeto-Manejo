package sdds.react.nativo.initial

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import sdds.react.nativo.R

class InitialScreen : AppCompatActivity() {
    var login = "admin"
    var senha = "123"
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_initial)
    }

    fun entrarSessao (view: View){
        var txt_login = findViewById<TextView>(R.id.txt_login).text
        var txt_senha = findViewById<TextView>(R.id.txt_senha).text

        if(txt_login.toString().equals(login) && txt_senha.toString().equals(senha)){
            startActivity(Intent(this, InitialScreenNav::class.java))
        }else{
            Toast.makeText(this, "Login ou senha invalido!", Toast.LENGTH_LONG).show()
        }
    }

}
