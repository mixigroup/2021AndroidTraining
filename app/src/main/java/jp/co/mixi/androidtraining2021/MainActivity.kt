package jp.co.mixi.androidtraining2021

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val startButton = findViewById<Button>(R.id.startButton)
        var job: Job? = null
        startButton.setOnClickListener {
            if (job?.isActive == true) {
                return@setOnClickListener
            }
            job = lifecycleScope.launch {
                val textView = findViewById<TextView>(R.id.textView)
                var count = 0
                try {
                    while (true) {
                        textView.text = "${count}秒"
                        count++
                        delay(1000)
                    }
                } finally {
                    textView.text = "停止"
                }
            }
        }

        val stopButton = findViewById<Button>(R.id.stopButton)
        stopButton.setOnClickListener {
            job?.cancel()
        }
    }
}