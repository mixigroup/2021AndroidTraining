package jp.co.mixi.androidtraining2021

import android.net.Uri
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import jp.co.mixi.androidtraining2021.model.Repository
import okhttp3.OkHttpClient
import okhttp3.Request

class GithubApiRepository {
    companion object {
        private const val API_ENDPOINT = "https://api.github.com/"
        private val CLIENT = OkHttpClient()
        private val MOSHI = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
    }

    fun getRepository(owner: String, repo: String): Repository {
        val uri = Uri.parse(API_ENDPOINT).buildUpon()
            .appendPath("repos")
            .appendPath(owner)
            .appendPath(repo)
        val request = Request.Builder().url(uri.toString()).build()
        val response = CLIENT.newCall(request).execute()
        val json = response.body?.string() ?: ""

        return MOSHI.adapter(Repository::class.java).fromJson(json)!!
    }
}
