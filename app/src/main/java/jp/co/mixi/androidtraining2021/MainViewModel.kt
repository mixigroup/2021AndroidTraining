package jp.co.mixi.androidtraining2021

import android.app.Application
import android.text.format.DateFormat
import android.util.Log
import androidx.lifecycle.*
import jp.co.mixi.androidtraining2021.model.Repository
import jp.co.mixi.androidtraining2021.model.RepositorySummary
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.*

class MainViewModel(application: Application) : AndroidViewModel(application) {

    val repositories = MutableLiveData<List<RepositorySummary>>()

    fun loadRepositoryList(owner: String) {
        viewModelScope.launch {
            try {
                val response = withContext(Dispatchers.IO) {
                    GithubApiRepository().getRepositoryList(owner)
                }
                repositories.value = response
            } catch (e: Exception) {
                repositories.value = emptyList()
                Log.e("MixiLesson", "Error", e)
            }
        }
    }
}
