package com.example.shopperapp.ui.wrapper

import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun <T> SLEWrapper(
    data:T,
    loadingContent: @Composable () -> Unit = { CircularProgressIndicator(modifier = Modifier.size(15.dp)) },
    errorContent: @Composable (message: String) -> Unit = { message -> Text(text = message) },
    successContent: @Composable (data: UIEvents) -> Unit
) {
    /*if(data is HomeScreenUIEvents){
        when (data) {
            is HomeScreenUIEvents.Loading -> loadingContent()
            is HomeScreenUIEvents.Success -> successContent(data)
            is HomeScreenUIEvents.Error -> errorContent(data.message)
        }
    }*/

}