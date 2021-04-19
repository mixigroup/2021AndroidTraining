package jp.co.mixi.androidtraining2021

import android.app.Application
import android.text.format.DateFormat
import androidx.lifecycle.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.*

class MainViewModel(application: Application) : AndroidViewModel(application) {

    companion object {
        const val TIME_ZONE_JAPAN = 0
        const val TIME_ZONE_HAWAII = 1
        const val TIME_ZONE_BEIJING = 2
        const val TIME_ZONE_INDIA = 3
    }

    private val currentEpochTime = liveData<Long> {
        while (true) {
            emit(System.currentTimeMillis())
            delay(1000)
        }
    }

    val timeZoneSelection = MutableLiveData<Int>(TIME_ZONE_JAPAN)

    val clockText = timeZoneSelection.switchMap { tz ->
        currentEpochTime.map {
            val timeZonId = when (tz) {
                TIME_ZONE_JAPAN -> "GMT+9:00"
                TIME_ZONE_HAWAII -> "GMT-10:00"
                TIME_ZONE_BEIJING -> "GMT+8:00"
                TIME_ZONE_INDIA -> "GMT+5:30"
                else -> ""
            }

            val calendar = Calendar.getInstance(TimeZone.getTimeZone(timeZonId))
            calendar.timeInMillis = it

            DateFormat.format("yyyy-MM-dd HH:mm:ss", calendar)
        }
    }

}
