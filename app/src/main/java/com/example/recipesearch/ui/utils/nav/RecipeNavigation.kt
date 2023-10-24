package com.example.recipesearch.ui.utils.nav

import NavItem
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.recipesearch.ui.screens.save.SaveScreen
import com.example.recipesearch.ui.screens.search.SearchScreen
import com.example.recipesearch.ui.screens.web.WebViewScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RecipeNavigation() {
    val navController = rememberNavController()
    val items = listOf(
        NavItem(title = Screen.SearchScreen.route, selectedIcon = Icons.Filled.Search, unselectedIcon = Icons.Outlined.Search),
        NavItem(title = Screen.SaveScreen.route, selectedIcon = Icons.Filled.Star, unselectedIcon = Icons.Outlined.Star)
    )
    var selectedItemIndex by rememberSaveable {
        mutableIntStateOf(0)
    }
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Scaffold(content = {
            Surface(modifier = Modifier.padding(it)) {
                NavHost(navController = navController, startDestination = Screen.SearchScreen.route){
                    composable(Screen.SearchScreen.route){
                        SearchScreen(navController)
                    }
                    composable(Screen.SaveScreen.route){
                        SaveScreen(navController)
                    }
                    composable(
                        Screen.WebScreen.route + "/{url}", arguments = listOf(
                        navArgument("url"){
                            type = NavType.StringType
                        }
                    )){
                        WebViewScreen(it.arguments?.getString("url")!!)
                    }
                }
            }
        }, bottomBar = { NavigationBar {
            items.forEachIndexed { index, navItem ->
                NavigationBarItem(
                    selected = selectedItemIndex == index,
                    onClick = {
                        selectedItemIndex = index
                        navController.navigate(navItem.title)
                    },
                    label = { Text(text = navItem.title) },
                    alwaysShowLabel = false,
                    icon = {
                        Icon(imageVector =
                            if (index == selectedItemIndex){
                                navItem.selectedIcon
                            }else {
                                navItem.unselectedIcon
                            },
                            contentDescription = navItem.title
                        )
                    }
                )
            }
        }
        }
        )
    }
}

