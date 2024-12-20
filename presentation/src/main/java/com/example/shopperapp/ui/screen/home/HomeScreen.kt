package com.example.presentation.ui.screen.home

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.domain.model.Product
import com.example.shopperapp.ui.components.list.row.HomeProductRow
import com.example.shopperapp.ui.components.searchbar.SearchBar
import com.example.shopperapp.ui.screen.home.HomeScreenUIEvents
import com.example.shopperapp.ui.screen.home.components.header.HomeScreenProfileHeader
import org.koin.androidx.compose.koinViewModel


@Composable
fun HomeScreen(navController: NavController, viewModel: HomeViewModel = koinViewModel()){

    val productState = viewModel.productState.collectAsState().value

    Scaffold {
        Surface(modifier = Modifier.fillMaxSize().padding(it)){
            when(productState){
                is HomeScreenUIEvents.Loading ->{
                    CircularProgressIndicator()
                }
                is HomeScreenUIEvents.Success->{
                    HomeScreenContent(productState.featured,productState.popularProducts)
                }
                is HomeScreenUIEvents.Error->{
                    Text(text = productState.message)
                }
            }
        }
    }
}


@Composable
fun HomeScreenContent(featured:List<Product>, popularProducts:List<Product>){
    LazyColumn {
        item{
            HomeScreenProfileHeader()
            Spacer(modifier = Modifier.size(16.dp))
            SearchBar("",{})
            Spacer(modifier = Modifier.size(16.dp))
            if(featured.isNotEmpty()){
                HomeProductRow(productList = featured, title = "Featured")
                Spacer(modifier = Modifier.size(8.dp))
            }
            if(popularProducts.isNotEmpty()){
                HomeProductRow(productList = popularProducts, title = "Popular Products ")
                //PopularProductColumn(productList = popularProducts, title = "Popular Products ")
            }
        }
    }
}

