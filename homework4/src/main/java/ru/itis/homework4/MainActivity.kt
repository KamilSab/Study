package ru.itis.homework4

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import by.kirich1409.viewbindingdelegate.viewBinding
import ru.itis.homework4.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        intent.getIntExtra("notification", -1).let{
            if(it == 1){
                Toast.makeText(
                    this,
                    "Запущено из уведомления",
                    Toast.LENGTH_LONG
                ).show()
            }
        }

        val theme = intent.getIntExtra("theme", 0)

        when(theme){
            1 -> setTheme(R.style.Theme_Themes_notifications_Red)
            2 -> setTheme(R.style.Theme_Themes_notifications_Green)
            3 -> setTheme(R.style.Theme_Themes_notifications_Yellow)
            else -> setTheme(R.style.Theme_Themes_notifications)
        }

        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction()
            .add(R.id.container, MainPageFragment())
            .commit()

    }
}