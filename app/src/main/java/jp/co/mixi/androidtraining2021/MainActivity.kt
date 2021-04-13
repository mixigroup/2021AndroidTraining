package jp.co.mixi.androidtraining2021

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Log.d("MixiLesson", "onCreate()")
    }

    override fun onStart() {
        super.onStart()

        Log.d("MixiLesson", "onStart()")
    }

    override fun onResume() {
        super.onResume()

        Log.d("MixiLesson", "onResume()")
    }

    override fun onPause() {
        super.onPause()

        Log.d("MixiLesson", "onPause()")
    }

    override fun onStop() {
        super.onStop()

        Log.d("MixiLesson", "onStop()")
    }

    override fun onDestroy() {
        super.onDestroy()

        Log.d("MixiLesson", "onDestroy()")
    }
}