package com.example.mvi_application

import com.arkivanov.essenty.instancekeeper.InstanceKeeper
import com.arkivanov.mvikotlin.core.instancekeeper.getStore
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.example.mvi_application.store.CalculatorStore
import com.example.mvi_application.store.CalculatorStoreFactory

class MainController(
    instanceKeeper: InstanceKeeper,
) {

    private val listStore =
        instanceKeeper.getStore {
            CalculatorStoreFactory(
                storeFactory = storeFactoryInstance,
            ).create()
        }

    var number = listStore.state.value


    fun onNumberIncremented() {
        listStore.accept(CalculatorStore.Intent.Increment)
    }

    fun onNumberDecremented() {
        listStore.accept(CalculatorStore.Intent.Decrement)
    }
}