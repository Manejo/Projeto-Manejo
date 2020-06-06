package sdds.react.nativo.Initial

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_initial.*
import sdds.react.nativo.R

class LoginScreenAct : AppCompatActivity() {
    var login = "admin"
    var senha = "123"
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_initial)
        setUpListeners()
    }

    fun setUpListeners() {
        btn_entrar.setOnClickListener{
            var txt_login = findViewById<TextView>(R.id.txt_login).text
            var txt_senha = findViewById<TextView>(R.id.txt_senha).text

            if(txt_login.toString().equals(login) && txt_senha.toString().equals(senha)){
                startActivity(Intent(this, PrincipalAct::class.java))
            }else{
                Toast.makeText(this, "Login ou senha invalido!", Toast.LENGTH_LONG).show()
            }
        }
    }
}
