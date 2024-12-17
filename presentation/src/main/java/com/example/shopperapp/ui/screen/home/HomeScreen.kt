package com.example.presentation.ui.screen.home

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.presentation.ui.components.ProductCard
import org.koin.androidx.compose.koinViewModel


@Composable
fun HomeScreen(navController: NavController, viewModel: HomeViewModel = koinViewModel()){

    val productState = viewModel.productState.collectAsState().value

    when(productState){
        is HomeScreenUIEvents.Loading ->{
            CircularProgressIndicator()
        }
        is HomeScreenUIEvents.Success->{
            val data = productState.data
            LazyColumn {
                items(data){ product->
                    ProductCard(product)
                }
            }
        }
        is HomeScreenUIEvents.Error->{
            Text(text = productState.message)
        }
    }


}