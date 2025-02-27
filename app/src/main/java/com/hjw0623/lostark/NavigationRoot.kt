package com.hjw0623.lostark

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.hjw0623.character.presentation.character_manager.CharacterManagerScreenRoot
import com.hjw0623.character.presentation.character_overview.CharacterScreen
import com.hjw0623.character.presentation.character_search.CharacterSearchScreenRoot
import com.hjw0623.events.presentation.events.EventScreen
import org.koin.androidx.compose.koinViewModel

@Composable
fun NavigationRoot(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = BottomNavItem.CHARACTER_MANAGER.route,
        modifier = Modifier.fillMaxSize()
    ) {
        composable(BottomNavItem.CHARACTER_MANAGER.route) {
            CharacterManagerScreenRoot(
                onCharacterDeleteClick = { /* 삭제 로직 */ },
                onCharacterSettingClick = { /* 설정 로직 */ },
                onCharacterAddClick = { navController.navigate(NavigationRoutes.CharacterSearch.route) }
            )
        }

        composable(BottomNavItem.CHARACTER_SEARCH.route) {
            CharacterSearchScreenRoot(
                onSearchClick = {
                    navController.navigate(NavigationRoutes.CharacterScreen.createRoute(characterName = it))
                },
                viewModel = koinViewModel()
            )
        }

        composable(BottomNavItem.EVENT_SCREEN.route) {
            EventScreen(viewModel = koinViewModel())
        }

        composable(NavigationRoutes.CharacterScreen.route) { backStackEntry ->
            val characterName = backStackEntry.arguments?.getString("characterName") ?: ""
            CharacterScreen(navController, characterName)
        }
    }
}
