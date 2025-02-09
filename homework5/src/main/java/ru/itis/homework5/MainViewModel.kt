package ru.itis.homework5

import android.app.Application
import android.widget.Toast
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.AndroidViewModel
import kotlinx.coroutines.Dispatchers

class MainViewModel(application: Application) : AndroidViewModel(application) {

    private val coroutineManager = CoroutineManager(application)
    var selectedBehavior by mutableStateOf("Останавливать")

    fun startCoroutines(count: String, launchType: String, behavior: String, dispatcher: String) {
        val coroutineCount = count.toIntOrNull() ?: return
        val dispatcherType = when (dispatcher) {
            "Default" -> Dispatchers.Default
            "IO" -> Dispatchers.IO
            "Unconfined" -> Dispatchers.Unconfined
            "Main" -> Dispatchers.Main
            else -> Dispatchers.Default
        }
        val sequential = launchType == "Последовательно"
        coroutineManager.startCoroutines(coroutineCount, dispatcherType, sequential)
    }

    fun cancelCoroutines() {
        val canceled = coroutineManager.cancelAllCoroutines()
        Toast.makeText(getApplication(), "Отменено корутин: $canceled", Toast.LENGTH_SHORT).show()
    }

    fun updateSelectedBehavior(behavior: String) {
        selectedBehavior = behavior
    }
}