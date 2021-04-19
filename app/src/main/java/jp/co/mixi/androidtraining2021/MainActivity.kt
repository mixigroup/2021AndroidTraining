package jp.co.mixi.androidtraining2021

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import jp.co.mixi.androidtraining2021.model.RepositorySummary

class MainActivity : AppCompatActivity() {

    private val viewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val editText = findViewById<EditText>(R.id.editText)
        editText.setText("mixi-inc")

        val button = findViewById<Button>(R.id.button)
        button.setOnClickListener {
            val owner = editText.text.toString()
            viewModel.loadRepositoryList(owner)
        }

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        val adapter = MyAdapter(this)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        viewModel.repositories.observe(this) {
            adapter.repositories = it
            adapter.notifyDataSetChanged()
        }
    }
}

private class MyAdapter(private val activity: Activity) : RecyclerView.Adapter<MyViewHolder>() {

    var repositories: List<RepositorySummary> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val repository = repositories[position]
        holder.fullName.text = repository.full_name
        holder.description.text = repository.description
        holder.itemView.setOnClickListener {
            val intent = Intent(activity, SubActivity::class.java)
            intent.putExtra(SubActivity.EXTRA_REPOSITORY_OWNER, repository.owner.login)
            intent.putExtra(SubActivity.EXTRA_REPOSITORY_NAME, repository.name)
            activity.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return repositories.size
    }
}

private class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val fullName = itemView.findViewById<TextView>(R.id.fullName)
    val description = itemView.findViewById<TextView>(R.id.description)
}
