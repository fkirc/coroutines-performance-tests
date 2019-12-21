package com.fkirc.coroutinelaunchtests

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.fkirc.coroutinelaunchtests.util.PrimitiveAsyncTask
import kotlinx.coroutines.Dispatchers
import org.junit.Test
import org.junit.runner.RunWith
import timber.log.Timber


@RunWith(AndroidJUnit4::class)
class AndroidCoroutinesLaunchTimeTests {

    @Test
    fun singleDispatchersIO() {
        measureSingleCoroutineLaunch(Dispatchers.IO)
    }

    @Test
    fun singleDispatchersDefault() {
        measureSingleCoroutineLaunch(Dispatchers.Default)
    }

    @Test
    fun singleDispatchersMain() {
        measureSingleCoroutineLaunch(Dispatchers.Main)
    }

    @Test
    fun singleDispatchersUnconfined() {
        measureSingleCoroutineLaunch(Dispatchers.Unconfined)
    }

    @Test
    fun multipleDispatchersIO() {
        measureMultipleCoroutineLaunches(Dispatchers.IO)
    }

    @Test
    fun multipleDispatchersDefault() {
        measureMultipleCoroutineLaunches(Dispatchers.Default)
    }

    @Test
    fun multipleDispatchersMain() {
        measureMultipleCoroutineLaunches(Dispatchers.Main)
    }

    @Test
    fun multipleDispatchersUnconfined() {
        measureMultipleCoroutineLaunches(Dispatchers.Unconfined)
    }

    @Test
    fun singleAsyncTask() {
        runAndAssertTestTime(maxTimeInMillis = MAX_LAUNCH_TIME_SINGLE_COROUTINE) {
            PrimitiveAsyncTask.doAsync {
                Timber.d("Hello world from AsyncTask")
            }
        }
    }

    @Test
    fun multipleAsyncTasks() {
        runAndAssertTestTime(maxTimeInMillis = MAX_LAUNCH_TIME_MULTIPLE_COROUTINES) {
            for (counter in 1..NUMBER_OF_COROUTINES) {
                PrimitiveAsyncTask.doAsync {
                    Timber.d("Hello world from AsyncTask $counter")
                }
            }
        }
    }
}
