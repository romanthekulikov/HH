package com.roman_kulikov.hh.base

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.io.Closeable
import kotlin.coroutines.CoroutineContext

abstract class BaseViewModel<S: BaseState> : ViewModel, CoroutineScope {
    constructor() : super()
    constructor(vararg closeables: Closeable) : super(*closeables)

    override val coroutineContext: CoroutineContext = Dispatchers.IO + SupervisorJob()
    protected abstract val _state: MutableStateFlow<S>
    val state: StateFlow<S> by lazy { _state.asStateFlow() }
}