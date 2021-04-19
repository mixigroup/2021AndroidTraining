package jp.co.mixi.androidtraining2021

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.activity.viewModels

class SubActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_REPOSITORY_OWNER = "owner"
        const val EXTRA_REPOSITORY_NAME = "name"
    }

    private val viewModel by viewModels<SubViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sub)

        val ownerNameTextView = findViewById<TextView>(R.id.ownerName)
        viewModel.repositoryOwnerName.observe(this) {
            ownerNameTextView.text = it
        }
        val repositoryNameTextView = findViewById<TextView>(R.id.repositoryName)
        viewModel.repositoryName.observe(this) {
            repositoryNameTextView.text = it
        }
        val watchCountTextView = findViewById<TextView>(R.id.watchCount)
        viewModel.watchCount.observe(this) {
            watchCountTextView.text = it
        }
        val starCountTextView = findViewById<TextView>(R.id.starCount)
        viewModel.starCount.observe(this) {
            starCountTextView.text = it
        }
        val forkCountTextView = findViewById<TextView>(R.id.forkCount)
        viewModel.forkCount.observe(this) {
            forkCountTextView.text = it
        }

        if (viewModel.repositoryResponse.value == null) {
            val owner = intent.getStringExtra(EXTRA_REPOSITORY_OWNER)!!
            val name = intent.getStringExtra(EXTRA_REPOSITORY_NAME)!!
            viewModel.loadRepository(owner, name)
        }
    }
}