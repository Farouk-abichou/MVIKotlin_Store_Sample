package com.example.mvi_application.android

import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.arkivanov.essenty.instancekeeper.InstanceKeeper
import com.arkivanov.essenty.instancekeeper.InstanceKeeperDispatcher
import com.example.mvi_application.MainController

class MainActivity : ComponentActivity() {


    var store = MainController(InstanceKeeperDispatcher())

    @RequiresApi(Build.VERSION_CODES.P)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {



            MyApplicationTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {

                    Row() {
                        TextButton(onClick = {
                            store.onNumberIncremented()
                            Log.d("taggg","${store.number}")
                        }) {
                            Text("+")
                        }
                        Text(text = " ${store.number} ")

                        TextButton(onClick = {
                            store.onNumberDecremented()
                            Log.d("taggg","${store.number}")
                        }) {
                            Text("-")
                        }
                    }
                }
            }
        }
    }
}


