package com.example.mvi_application

import com.arkivanov.mvikotlin.core.store.Reducer
import com.arkivanov.mvikotlin.core.store.Store
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.arkivanov.mvikotlin.core.store.create
import com.arkivanov.mvikotlin.extensions.coroutines.CoroutineBootstrapper
import com.arkivanov.mvikotlin.extensions.coroutines.CoroutineExecutor
import com.example.mvi_application.CalculatorStore.*


internal class CalculatorStoreFactory(private val storeFactory: StoreFactory) {

    fun create(): CalculatorStore =
        object : CalculatorStore, Store<Intent, State, Nothing> by storeFactory.create(
            name = "CounterStore",
            initialState = State(),
            bootstrapper = BootstrapperImpl(),
            executorFactory = ::ExecutorImpl,
            reducer = ReducerImpl
        ) {
        }

    private sealed interface Action {
        class SetValue(val value: Long): Action
    }

    // ...

    private class BootstrapperImpl : CoroutineBootstrapper<Action>() {
        override fun invoke() {
            scope.launch {
                val sum = withContext(Dispatchers.Default) { (1L..1000000.toLong()).sum() }
                dispatch(Action.SetValue(sum))
            }
        }
    }

    private class ExecutorImpl : CoroutineExecutor<Intent, Action, State, Msg, Nothing>() {
        override fun executeAction(action: Action, getState: () -> State) =
            when (action) {
                is Action.SetValue -> dispatch(Msg.Value(action.value))
            }

        // ...
    }

    // ...
}