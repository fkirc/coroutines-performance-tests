package com.fkirc.coroutinelaunchtests

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import timber.log.Timber
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
