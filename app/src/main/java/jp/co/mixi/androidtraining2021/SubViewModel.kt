package jp.co.mixi.androidtraining2021

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.map
import jp.co.mixi.androidtraining2021.model.Repository

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

}