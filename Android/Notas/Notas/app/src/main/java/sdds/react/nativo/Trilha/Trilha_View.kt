package sdds.react.nativo.Trilha

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_trilha_view.*
import sdds.react.nativo.R

class Trilha_View : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_trilha_view)

        val extras = intent.extras

        if (extras != null) {
            val NomeTrilha = extras.getString("mensagem")
            txtNome.setText(NomeTrilha)
        }
    }
}
