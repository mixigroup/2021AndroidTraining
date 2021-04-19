package jp.co.mixi.androidtraining2021

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private val viewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val button = findViewById<Button>(R.id.button)
        button.setOnClickListener {
            val intent = Intent(this, SubActivity::class.java)
            intent.putExtra(SubActivity.EXTRA_REPOSITORY_OWNER, "mixi-inc")
            intent.putExtra(SubActivity.EXTRA_REPOSITORY_NAME, "AndroidTraining")

            startActivity(intent)
        }
    }
}