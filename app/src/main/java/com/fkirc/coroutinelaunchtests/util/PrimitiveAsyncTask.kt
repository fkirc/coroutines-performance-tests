package com.fkirc.coroutinelaunchtests.util

import android.os.AsyncTask

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
