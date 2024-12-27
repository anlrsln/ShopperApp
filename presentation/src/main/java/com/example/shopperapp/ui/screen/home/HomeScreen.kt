package com.example.presentation.ui.screen.home

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.domain.model.Product
import com.example.shopperapp.ui.components.searchbar.SearchBar
import com.example.shopperapp.ui.screen.home.components.header.HomeScreenProfileHeader
import com.example.shopperapp.ui.components.loading.LoadingIndicator
import com.example.shopperapp.ui.screen.home.components.list.categories.HomeScreenCategoriesView
import com.example.shopperapp.ui.screen.home.components.list.popular.HomeScreenFeaturedProductsView
import com.example.shopperapp.ui.screen.home.components.list.popular.HomeScreenPopularProductsView
import com.example.shopperapp.ui.wrapper.HomeScreenUIEvents
import org.koin.androidx.compose.koinViewModel


@Composable
fun HomeScreen(navController: NavController, viewModel: HomeViewModel = koinViewModel()){

    val uiState = viewModel.uiState.collectAsState().value
    val featuredProducts = remember {
        mutableStateOf<List<Product>>(emptyList())
    }
    val popularProducts = remember {
        mutableStateOf<List<Product>>(emptyList())
    }
    val categories = remember {
        mutableStateOf<List<String>>(emptyList())
    }


    Scaffold {
        Surface(modifier = Modifier.fillMaxSize().padding(it)){
            when(uiState){
                is HomeScreenUIEvents.Loading ->{
                    LoadingIndicator()
                }
                is HomeScreenUIEvents.Success->{
                    /*featuredProducts.value = uiState.featured
                    popularProducts.value = uiState.popularProducts
                    categories.value = uiState.categories*/
                    HomeScreenContent(uiState.featured,uiState.popularProducts,uiState.categories)
                }
                is HomeScreenUIEvents.Error->{
                    Text(text = uiState.message)
                }
            }

        }

    }

}



@Composable
fun HomeScreenContent(
    featured: List<Product>,
    popularProducts: List<Product>,
    categories: List<String>) {
    LazyColumn {
        item {
            Spacer(modifier = Modifier.size(16.dp))
            HomeScreenProfileHeader()
            Spacer(modifier = Modifier.size(16.dp))
            SearchBar("", {})
            Spacer(modifier = Modifier.size(16.dp))
        }
        if (categories.isNotEmpty()) {
            item {
                HomeScreenCategoriesView(categories)
                Spacer(modifier = Modifier.size(16.dp))
            }
        }
        if (featured.isNotEmpty()) {
            item {
                HomeScreenFeaturedProductsView(productList = featured, title = "Featured")
                Spacer(modifier = Modifier.size(16.dp))
            }
        }
        if (popularProducts.isNotEmpty()) {
            item {
                HomeScreenPopularProductsView(productList = popularProducts, title = "Popular Products ")
                Spacer(modifier = Modifier.size(16.dp))
            }
        }
    }
}

