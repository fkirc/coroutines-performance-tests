package com.fkirc.coroutinelaunchtests.util

import android.os.AsyncTask
import timber.log.Timber

class PrimitiveAsyncTask(val runnable: () -> Unit) : AsyncTask<Void, Void, Void>() {

    companion object {
        fun doAsync(runnable: () -> Unit) {
            PrimitiveAsyncTask(runnable).execute()
        }
    }

    override fun doInBackground(vararg params: Void?): Void? {
        runnable()
        return null
    }
}

fun runTimeMeasured(description: String, runnable: () -> Unit) {
    val start = System.nanoTime()
    runnable()
    val time = System.nanoTime() - start
    val timeInMillis = (time / 1000).toFloat() / 1000f
    Timber.d("Took %s milliseconds to run %s", timeInMillis, description)
}
