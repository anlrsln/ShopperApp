package com.example.shopper

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarDefaults
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.presentation.ui.screen.home.HomeScreen
import com.example.shopper.ui.theme.ShopperTheme
import com.example.shopperapp.R


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ShopperTheme {
                val navController = rememberNavController()
                Scaffold(modifier = Modifier
                    .fillMaxSize(),
                    bottomBar = {
                        BottomNavigationBar(navController)
                    }
                ) {
                    Surface(modifier = Modifier
                        .fillMaxSize()
                        .padding(it)){
                        NavHost(navController = navController, startDestination = "home" ){
                            composable("home") {
                                HomeScreen(navController = navController)
                            }
                            composable("cart") {
                                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
                                    Text(text = "Cart")
                                }
                            }
                            composable("profile") {
                                Box(modifier = Modifier.fillMaxSize(),contentAlignment = Alignment.Center){
                                    Text(text = "Profile")
                                }
                            }
                        }
                    }
                }
            }
        }
    }



    @Composable
    fun BottomNavigationBar(navController: NavController) {
        NavigationBar {
            //current route
            val currentRoute = navController.currentDestination.route
            val items = listOf(
                BottomNavItems.Home,
                BottomNavItems.Cart,
                BottomNavItems.Profile
            )
            items.forEach{item->
                NavigationBarItem(
                    selected = currentRoute==item.route,
                    onClick = {
                        navController.navigate(item.route){
                            navController.graph.startDestinationRoute?.let{startRoute ->
                                popUpTo(startRoute){
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        }
                    },
                    icon = {
                        Image(
                            painter = painterResource(id = item.icon),
                            contentDescription = null,
                            colorFilter = ColorFilter.tint(
                                if (currentRoute == item.route) {
                                    MaterialTheme.colorScheme.primary
                                } else Color.LightGray
                            )
                        )
                    },
                    colors = NavigationBarItemDefaults.colors().copy(
                        selectedIconColor = MaterialTheme.colorScheme.primary,
                        unselectedIconColor = Color.LightGray
                    )
                )
            }
        }
    }

    sealed class BottomNavItems(val route: String, val title: String, val icon: Int) {
        object Home : BottomNavItems("home", "Home", icon = R.drawable.ic_home)
        object Cart : BottomNavItems("cart", "Cart", icon = R.drawable.ic_cart)
        object Profile : BottomNavItems("profile", "Profile", icon = R.drawable.ic_profile_bn)
    }












}


