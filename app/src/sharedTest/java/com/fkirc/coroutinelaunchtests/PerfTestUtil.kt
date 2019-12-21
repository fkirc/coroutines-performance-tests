package com.fkirc.coroutinelaunchtests

import timber.log.Timber
import java.lang.IllegalArgumentException

private fun assertTestTime(timeInNanos: Long, maxTimeInMillis: Long) {
    if (timeInNanos < 0) throw IllegalArgumentException()
    if (maxTimeInMillis <= 0) throw IllegalArgumentException()

    val timeInMillis = (timeInNanos / 1000).toFloat() / 1000f
    if (timeInMillis <= maxTimeInMillis) {
        Timber.d("Took $timeInMillis milliseconds")
    } else {
        throw Exception("Maxtime is $maxTimeInMillis milliseconds, but took $timeInMillis milliseconds")
    }
}

fun runAndAssertTestTime(maxTimeInMillis: Long, runnable: () -> Unit) {
    val start = System.nanoTime()
    runnable()
    assertTestTime(timeInNanos = System.nanoTime() - start, maxTimeInMillis = maxTimeInMillis)
}
