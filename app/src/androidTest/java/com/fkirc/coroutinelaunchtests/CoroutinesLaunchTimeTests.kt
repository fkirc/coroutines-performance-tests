package com.fkirc.coroutinelaunchtests

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.fkirc.coroutinelaunchtests.util.PrimitiveAsyncTask
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.junit.Test
import org.junit.runner.RunWith
import timber.log.Timber
import java.lang.IllegalArgumentException
import kotlin.coroutines.CoroutineContext


@RunWith(AndroidJUnit4::class)
class CoroutinesLaunchTimeTests {

    @Test
    fun singleCoroutineDispatchersIO() {
        measureSingleCoroutineLaunch(Dispatchers.IO)
    }

    @Test
    fun singleCoroutineDispatchersDefault() {
        measureSingleCoroutineLaunch(Dispatchers.Default)
    }

    @Test
    fun singleCoroutineDispatchersMain() {
        measureSingleCoroutineLaunch(Dispatchers.Main)
    }

    @Test
    fun singleCoroutineDispatchersUnconfined() {
        measureSingleCoroutineLaunch(Dispatchers.Unconfined)
    }

    @Test
    fun multipleCoroutinesDispatchersIO() {
        measureMultipleCoroutineLaunches(Dispatchers.IO)
    }

    @Test
    fun multipleCoroutinesDispatchersDefault() {
        measureMultipleCoroutineLaunches(Dispatchers.Default)
    }

    @Test
    fun multipleCoroutinesDispatchersMain() {
        measureMultipleCoroutineLaunches(Dispatchers.Main)
    }

    @Test
    fun multipleCoroutinesDispatchersUnconfined() {
        measureMultipleCoroutineLaunches(Dispatchers.Unconfined)
    }

    private fun measureSingleCoroutineLaunch(coroutineContext: CoroutineContext) {
        runAndAssertTestTime(maxTimeInMillis = 1) {
            GlobalScope.launch(coroutineContext) {
                Timber.d("Hello world from coroutine")
            }
        }
    }

    private fun measureMultipleCoroutineLaunches(coroutineContext: CoroutineContext) {
        val numberOfCoroutines = 50
        runAndAssertTestTime(maxTimeInMillis = 5) {
            for (counter in 1..numberOfCoroutines) {
                GlobalScope.launch(coroutineContext) {
                    Timber.d("Hello world from coroutine $counter")
                }
            }
        }
    }

    @Test
    fun singleAsyncTask() {
        runAndAssertTestTime(maxTimeInMillis = 1) {
            PrimitiveAsyncTask.doAsync {
                Timber.d("Hello world from AsyncTask")
            }
        }
    }

    @Test
    fun multipleAsyncTasks() {
        val numberOfAsyncTasks = 50
        runAndAssertTestTime(maxTimeInMillis = 2) {
            for (counter in 1..numberOfAsyncTasks) {
                PrimitiveAsyncTask.doAsync {
                    Timber.d("Hello world from AsyncTask $counter")
                }
            }
        }
    }
}

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

private fun runAndAssertTestTime(maxTimeInMillis: Long, runnable: () -> Unit) {
    val start = System.nanoTime()
    runnable()
    assertTestTime(timeInNanos = System.nanoTime() - start, maxTimeInMillis = maxTimeInMillis)
}
