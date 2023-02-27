package com.example.mvi_application

import com.arkivanov.mvikotlin.core.store.Store
import com.example.mvi_application.CalculatorStore.*

internal interface CalculatorStore : Store<Intent, State, Nothing> {

    sealed interface Intent {
        object Increment : Intent
        object Decrement : Intent
        data class Sum(val n: Int): Intent // <-- Add this line
    }

    data class State(
        val value: Long = 0L
    )
}