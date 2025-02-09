package ru.itis.homework5

import android.app.Application
import android.widget.Toast
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext

class CoroutineManager(application: Application) {
//    private val coroutineScope = CoroutineScope(SupervisorJob())
    private val activeJobs = mutableListOf<Job>()
    private val context = application.applicationContext

    fun startCoroutines(count: Int, dispatcher: CoroutineDispatcher, sequential: Boolean) {
        val coroutineScope = CoroutineScope(dispatcher)
        coroutineScope.launch {
            repeat(count) {
                val job = launch {
                    try {
                        delay(5000L)
                        println("TEST TAG - Coroutine Started")
                    } catch (e: Exception) {
                        withContext(Dispatchers.Main) {
                            Toast.makeText(context, "Ошибка в корутине: ${e.message}", Toast.LENGTH_SHORT).show()
                        }
                        e.printStackTrace()
                    }
                }
                activeJobs.add(job)
                if (sequential) {
                    job.join()
                }
            }
        }
    }

    fun cancelAllCoroutines(): Int {
        val canceledCount = activeJobs.count { it.isActive }
        activeJobs.forEach { it.cancel() }
        activeJobs.clear()
        return canceledCount
    }
}