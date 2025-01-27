package com.roman_kulikov.hh.ui.base

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancelChildren
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.Closeable
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext

abstract class BaseViewModel<S: BaseState> : ViewModel, CoroutineScope, ProgressWorker {
    constructor() : super()
    constructor(vararg closeables: Closeable) : super(*closeables)

    override val coroutineContext: CoroutineContext = Dispatchers.IO + SupervisorJob()

    protected abstract val _state: MutableStateFlow<S>
    val state: StateFlow<S> by lazy { _state.asStateFlow() }

    fun CoroutineScope.launchWithProgress(
        context: CoroutineContext = EmptyCoroutineContext,
        start: CoroutineStart = CoroutineStart.DEFAULT,
        block: suspend CoroutineScope.() -> Unit
    ) {
        onStartJob()
        launch(context, start) {
            block()
            withContext(Dispatchers.Main) {
                onFinishJob()
            }
        }
    }

    fun stopJob() {
        coroutineContext.cancelChildren()
    }

    override fun onStartJob() { /* nothing */ }
    override fun onFinishJob() { /* nothing */ }
}