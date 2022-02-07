package com.gtxtreme.template.interactor.base

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.withContext

@ExperimentalCoroutinesApi
class TestCoroutineContextController(
    val testCoroutineDispatcher: TestCoroutineDispatcher
) : CoroutineContextController {

    override val dispatcherDefault: CoroutineDispatcher
        get() = testCoroutineDispatcher
    override val dispatcherIO: CoroutineDispatcher
        get() = testCoroutineDispatcher

    override suspend fun <T> switchToDefault(block: suspend CoroutineScope.() -> T): T =
        withContext(dispatcherDefault) {
            block()
        }

    override suspend fun <T> switchToIO(block: suspend CoroutineScope.() -> T): T =
        withContext(dispatcherIO) {
            block()
        }
}
