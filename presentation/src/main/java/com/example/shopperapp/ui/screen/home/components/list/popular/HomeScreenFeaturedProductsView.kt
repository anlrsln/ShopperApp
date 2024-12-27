package com.example.shopperapp.ui.screen.home.components.list.popular

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.domain.model.Product
import com.example.presentation.ui.components.ProductCard

@Composable
fun HomeScreenFeaturedProductsView(productList:List<Product>,title: String){
    Column {
        Box(modifier = Modifier.padding(horizontal = 16.dp).fillMaxWidth()){
            Text(text = title, style = MaterialTheme.typography.titleMedium, modifier = Modifier.align(alignment = Alignment.CenterStart))
            Text(text = "See All", style = MaterialTheme.typography.bodyMedium,modifier = Modifier.align(alignment = Alignment.CenterEnd))
        }
        Spacer(modifier = Modifier.padding(8.dp))
        LazyRow {
            items(productList){product->
                ProductCard(product)
            }
        }
    }
}