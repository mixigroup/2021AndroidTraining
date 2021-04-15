package jp.co.mixi.androidtraining2021

import android.os.AsyncTask
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private var task: MyAsyncTask? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        task = MyAsyncTask().apply {
            execute(10)
        }
    }

    override fun onDestroy() {
        task?.cancel(true)
        task = null
        super.onDestroy()
    }

    private inner class MyAsyncTask : AsyncTask<Int, String, String>() {
        override fun doInBackground(vararg params: Int?): String {
            for (i in 1..params[0]!!) {
                publishProgress("途中経過$i")
                Thread.sleep(1000)
            }
            return "完了"
        }

        override fun onProgressUpdate(vararg values: String?) {
            val textView = findViewById<TextView>(R.id.textView)
            textView.text = values[0]
        }

        override fun onPostExecute(result: String?) {
            val textView = findViewById<TextView>(R.id.textView)
            textView.text = result
        }
    }
}