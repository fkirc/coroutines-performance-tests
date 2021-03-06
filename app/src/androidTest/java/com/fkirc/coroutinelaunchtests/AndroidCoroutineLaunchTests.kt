package com.fkirc.coroutinelaunchtests

import androidx.test.ext.junit.runners.AndroidJUnit4
import kotlinx.coroutines.Dispatchers
import org.junit.FixMethodOrder
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.MethodSorters


@RunWith(AndroidJUnit4::class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
class AndroidCoroutinesLaunchTimeTests {

    @Test
    fun a1_singleDispatchersIO() {
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
    fun singleThread() {
        measureSingleThreadLaunch()
    }

    @Test
    fun multipleThreads() {
        measureMultipleThreadLaunches()
    }

    @Test
    fun singleAsyncTask() {
        measureSingleAsyncTaskLaunch()
    }

    @Test
    fun multipleAsyncTasks() {
        measureMultipleAsyncTaskLaunches()
    }
}
