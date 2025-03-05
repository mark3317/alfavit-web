package ru.markn.alfavitweb.pres.utils.mvi

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*

abstract class MviViewModel<S: IMviState>(
    initialState: S
) : ViewModel() {
    private val _stateFlow = MutableStateFlow(initialState)

    private val _observingFlow: Flow<Unit> = flow {
        emit(Unit)
        coroutineScope {
            observableFlows.map {
                async(Dispatchers.Default) {
                    it.collect()
                }
            }.awaitAll()
        }
    }.flowOn(Dispatchers.Default)

    protected open val observableFlows: List<Flow<*>> = listOf()

    val stateFlow: SharedFlow<S> =
        combine(_stateFlow, _observingFlow) { s, _ -> s }
            .distinctUntilChanged()
            .shareIn(viewModelScope, SharingStarted.WhileSubscribed())

    val state: S
        get() = _stateFlow.value

    fun updateState(transform: S.() -> S): S {
        val newState = state.transform()
        _stateFlow.value = newState
        return newState.also { println("updateState: $it") }
    }
}