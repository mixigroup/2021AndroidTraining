package jp.co.mixi.androidtraining2021

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private val viewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val textView = findViewById<TextView>(R.id.textView)
        viewModel.clockText.observe(this) {
            textView.text = it
        }

        val buttonTzJapan = findViewById<Button>(R.id.buttonTzJapan)
        buttonTzJapan.setOnClickListener {
            viewModel.timeZoneSelection.value = MainViewModel.TIME_ZONE_JAPAN
        }
        val buttonTzHawaii = findViewById<Button>(R.id.buttonTzHawaii)
        buttonTzHawaii.setOnClickListener {
            viewModel.timeZoneSelection.value = MainViewModel.TIME_ZONE_HAWAII
        }
        val buttonTzBeijing = findViewById<Button>(R.id.buttonTzBeijing)
        buttonTzBeijing.setOnClickListener {
            viewModel.timeZoneSelection.value = MainViewModel.TIME_ZONE_BEIJING
        }
        val buttonTzIndia = findViewById<Button>(R.id.buttonTzIndia)
        buttonTzIndia.setOnClickListener {
            viewModel.timeZoneSelection.value = MainViewModel.TIME_ZONE_INDIA
        }
    }
}