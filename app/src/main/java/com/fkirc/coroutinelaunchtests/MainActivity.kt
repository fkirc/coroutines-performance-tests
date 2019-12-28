package com.fkirc.coroutinelaunchtests

import android.app.Application
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.fkirc.coroutinelaunchtests.util.PrimitiveAsyncTask
import com.fkirc.coroutinelaunchtests.util.runTimeMeasured
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import timber.log.Timber

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        launchSampleCoroutine("MainActivity coroutine")
    }
}

class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
        PrimitiveAsyncTask.doAsync {
            // Preload coroutine library asynchronously to avoid slow launches afterwards.
            launchSampleCoroutine("Application.onCreate async coroutine")
        }
    }
}

fun launchSampleCoroutine(description: String) {
    runTimeMeasured(description) {
        GlobalScope.launch(Dispatchers.IO) {
            Timber.d("Hello world from %s", description)
        }
    }
}
