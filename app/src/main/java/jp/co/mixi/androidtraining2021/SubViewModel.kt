package jp.co.mixi.androidtraining2021

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.map
import androidx.lifecycle.viewModelScope
import jp.co.mixi.androidtraining2021.model.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SubViewModel(application: Application) : AndroidViewModel(application) {

    val repositoryResponse = MutableLiveData<Repository>()

    val repositoryOwnerName = repositoryResponse.map {
        "オーナー名: ${it.owner.login}"
    }
    val repositoryName = repositoryResponse.map {
        "レポジトリ名: ${it.name}"
    }
    val watchCount = repositoryResponse.map {
        "ウォッチ数: ${it.subscribers_count}"
    }
    val starCount = repositoryResponse.map {
        "スター数: ${it.stargazers_count}"
    }
    val forkCount = repositoryResponse.map {
        "フォーク数: ${it.forks_count}"
    }

    fun loadRepository(owner: String, repo: String) {
        viewModelScope.launch {
            try {
                val response = withContext(Dispatchers.IO) {
                    GithubApiRepository().getRepository(owner, repo)
                }
                repositoryResponse.value = response
            } catch (e: Exception) {
                Log.e("MixiLesson", "Error", e)
            }
        }
    }
}