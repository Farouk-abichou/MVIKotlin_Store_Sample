package com.example.mvi_application.store

import com.arkivanov.mvikotlin.core.store.Store
import com.example.mvi_application.store.CalculatorStore.*

internal interface CalculatorStore : Store<Intent, State, Nothing> {

    sealed interface Intent {
        object Increment : Intent
        object Decrement : Intent
    }

    data class State(
        val value: Long = 0
    )
}