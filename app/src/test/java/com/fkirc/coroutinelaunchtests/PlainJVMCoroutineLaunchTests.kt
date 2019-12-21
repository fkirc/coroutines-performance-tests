package com.fkirc.coroutinelaunchtests

import kotlinx.coroutines.Dispatchers
import org.junit.FixMethodOrder
import org.junit.Test
import org.junit.runners.MethodSorters

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
class PlainJVMCoroutinesLaunchTimeTests {

    @Test
    fun a1_singleDispatchersIO() {
        measureSingleCoroutineLaunch(Dispatchers.IO)
    }

    @Test
    fun singleDispatchersDefault() {
        measureSingleCoroutineLaunch(Dispatchers.Default)
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

    @Test(expected = RuntimeException::class)
    fun tryAsyncTaskWithoutAndroidMock() {
        measureSingleAsyncTaskLaunch()
    }
}
