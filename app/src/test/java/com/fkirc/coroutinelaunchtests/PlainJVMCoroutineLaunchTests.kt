package com.fkirc.coroutinelaunchtests

import kotlinx.coroutines.Dispatchers
import org.junit.Test


class PlainJVMCoroutinesLaunchTimeTests {

    @Test
    fun singleDispatchersIO() {
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
}
