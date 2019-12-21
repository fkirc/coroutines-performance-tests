package com.fkirc.coroutinelaunchtests

import com.fkirc.coroutinelaunchtests.util.PrimitiveAsyncTask
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import timber.log.Timber
import kotlin.concurrent.thread
import kotlin.coroutines.CoroutineContext

val MAX_LAUNCH_TIME_SINGLE_COROUTINE = 0.5f
val MAX_LAUNCH_TIME_MULTIPLE_COROUTINES = 5f
val NUMBER_OF_COROUTINES = 50

fun measureSingleCoroutineLaunch(coroutineContext: CoroutineContext) {
    runAndAssertTestTime(maxTimeInMillis = MAX_LAUNCH_TIME_SINGLE_COROUTINE) {
        GlobalScope.launch(coroutineContext) {
            Timber.d("Hello world from coroutine")
        }
    }
}

fun measureMultipleCoroutineLaunches(coroutineContext: CoroutineContext) {
    val numberOfCoroutines = NUMBER_OF_COROUTINES
    runAndAssertTestTime(maxTimeInMillis = MAX_LAUNCH_TIME_MULTIPLE_COROUTINES) {
        for (counter in 1..numberOfCoroutines) {
            GlobalScope.launch(coroutineContext) {
                Timber.d("Hello world from coroutine $counter")
            }
        }
    }
}

fun measureSingleThreadLaunch() {
    runAndAssertTestTime(maxTimeInMillis = MAX_LAUNCH_TIME_SINGLE_COROUTINE) {
        thread(start = true) {
            Timber.d("Hello world from Thread")

        }
    }
}

fun measureMultipleThreadLaunches() {
    runAndAssertTestTime(maxTimeInMillis = MAX_LAUNCH_TIME_MULTIPLE_COROUTINES) {
        for (counter in 1..NUMBER_OF_COROUTINES) {
            thread(start = true) {
                Timber.d("Hello world from Thread $counter")

            }
        }
    }
}

fun measureSingleAsyncTaskLaunch() {
    runAndAssertTestTime(maxTimeInMillis = MAX_LAUNCH_TIME_SINGLE_COROUTINE) {
        PrimitiveAsyncTask.doAsync {
            Timber.d("Hello world from AsyncTask")
        }
    }
}

fun measureMultipleAsyncTaskLaunches() {
    runAndAssertTestTime(maxTimeInMillis = MAX_LAUNCH_TIME_MULTIPLE_COROUTINES) {
        for (counter in 1..NUMBER_OF_COROUTINES) {
            PrimitiveAsyncTask.doAsync {
                Timber.d("Hello world from AsyncTask $counter")
            }
        }
    }
}
