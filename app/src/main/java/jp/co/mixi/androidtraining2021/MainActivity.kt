package jp.co.mixi.androidtraining2021

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        val adapter = MyAdapter()
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        val textList = mutableListOf<String>()
        for (i in 0..100) {
            textList.add("要素$i")
        }
        adapter.textList = textList
        adapter.notifyDataSetChanged()
    }
}

private class MyAdapter : RecyclerView.Adapter<MyViewHolder>() {

    var textList: List<String> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.list_item, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.textView.text = textList[position]
        holder.itemView.setBackgroundColor(
            if (position % 2 == 0) {
                Color.WHITE
            } else {
                Color.LTGRAY
            }
        )
    }

    override fun getItemCount(): Int {
        return textList.size
    }
}

private class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val textView = itemView.findViewById<TextView>(R.id.textView)
}