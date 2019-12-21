package com.fkirc.coroutinelaunchtests

import com.fkirc.coroutinelaunchtests.util.PrimitiveAsyncTask
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import timber.log.Timber
import kotlin.coroutines.CoroutineContext


fun measureSingleCoroutineLaunch(coroutineContext: CoroutineContext) {
    runAndAssertTestTime(maxTimeInMillis = 1) {
        GlobalScope.launch(coroutineContext) {
            Timber.d("Hello world from coroutine")
        }
    }
}

fun measureMultipleCoroutineLaunches(coroutineContext: CoroutineContext) {
    val numberOfCoroutines = 50
    runAndAssertTestTime(maxTimeInMillis = 5) {
        for (counter in 1..numberOfCoroutines) {
            GlobalScope.launch(coroutineContext) {
                Timber.d("Hello world from coroutine $counter")
            }
        }
    }
}

fun measureSingleAsyncTask() {
    runAndAssertTestTime(maxTimeInMillis = 1) {
        PrimitiveAsyncTask.doAsync {
            Timber.d("Hello world from AsyncTask")
        }
    }
}

fun measureMultipleAsyncTasks() {
    val numberOfAsyncTasks = 50
    runAndAssertTestTime(maxTimeInMillis = 2) {
        for (counter in 1..numberOfAsyncTasks) {
            PrimitiveAsyncTask.doAsync {
                Timber.d("Hello world from AsyncTask $counter")
            }
        }
    }
}

