package com.hjw0623.lostark

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController


@Composable
fun MainScreen() {
    val navController = rememberNavController()

    val bottomBarScreens = listOf(
        BottomNavItem.CHARACTER_MANAGER.route,
        BottomNavItem.CHARACTER_SEARCH.route,
        BottomNavItem.EVENT_SCREEN.route
    )

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    Scaffold(
        bottomBar = {
            if (currentRoute in bottomBarScreens) {
                BottomNavigationBar(navController)
            }
        }
    ) { paddingValues ->
        Box(modifier = Modifier.padding(paddingValues)) {
            NavigationRoot(navController = navController)
        }
    }
}
