package com.example.shopperapp.ui.screen.home.components.list.categories

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp


@Composable
fun HomeScreenCategoriesView(categories:List<String>){
    Column(modifier = Modifier.padding(16.dp)) {
        Text(
            text = "Categories",
            style = MaterialTheme.typography.titleMedium,
        )
        LazyRow(modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 10.dp)
        ){
            items(categories){category->
                Box(modifier = Modifier
                    .padding(5.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(Color.White)
                ){
                    Text(
                        text = category.replaceFirstChar { it.uppercase() },
                        style = MaterialTheme.typography.bodyMedium,
                        modifier = Modifier
                            .padding(8.dp)
                    )
                }

            }
        }
    }
}