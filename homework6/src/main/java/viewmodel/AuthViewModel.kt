package viewmodel

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.itis.homework6.data.AppDatabase
import ru.itis.homework6.data.repository.AppRepository

class AuthViewModel(application: Application) : AndroidViewModel(application) {
    private val context = application.applicationContext
    private val db = AppDatabase.getDatabase(context)
    private val repository = AppRepository(db)
    private val prefs: SharedPreferences =
        context.getSharedPreferences("app_prefs", Context.MODE_PRIVATE)

    var isLoggedIn by mutableStateOf(prefs.getBoolean("logged_in", false))
        private set

    fun login(identifier: String, password: String, onResult: (Boolean) -> Unit) {
        viewModelScope.launch {
            val success = repository.authenticateUser(identifier, password)
            if (success) {
                isLoggedIn = true
                prefs.edit().putBoolean("logged_in", true).apply()
            }
            onResult(success)
        }
    }

    fun register(username: String, email: String, password: String, onResult: (Boolean) -> Unit) {
        viewModelScope.launch {
            val success = repository.registerUser(username, email, password)
            if (success) {
                isLoggedIn = true
                prefs.edit().putBoolean("logged_in", true).apply()
            }
            onResult(success)
        }
    }

    fun logout() {
        isLoggedIn = false
        prefs.edit().putBoolean("logged_in", false).apply()
    }
}