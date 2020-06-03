package sdds.react.nativo.Adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import sdds.react.nativo.R

class TrilhaButtonAdapter: RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val items: MutableList<String> = mutableListOf("Trilha 1", "Trilha 2", "Trilha 3")
    private lateinit var context: Context
    private lateinit var intent: Intent

    fun addAct(context: Context, intent: Intent){
        this.context = context
        this.intent = intent
    }

    fun addItem(message: String){
        items.add(message)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val card = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.trilha_card_button, parent, false)

        return MessageViewHolder(card)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val itemAtual = items[position]

        if (holder is MessageViewHolder){
            holder.btnView.text = itemAtual
            holder.btnView.setOnClickListener(){
                context.startActivity(intent.putExtra("mensagem", itemAtual))
            }
        }
    }

    class MessageViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val btnView: Button = itemView.findViewById(R.id.btnExibicao)
    }
}