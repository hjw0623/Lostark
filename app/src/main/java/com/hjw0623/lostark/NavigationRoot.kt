package com.hjw0623.lostark

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.hjw0623.character.presentation.character_add.CharacterAddScreenRoot
import com.hjw0623.character.presentation.character_manager.CharacterManagerScreenRoot
import com.hjw0623.character.presentation.character_overview.CharacterOverviewScreenRoot
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
                onCharacterAddClick = { navController.navigate(NavigationRoutes.CharacterAdd.createRoute()) }
            )
        }

        composable(BottomNavItem.CHARACTER_SEARCH.route) {
            CharacterSearchScreenRoot(
                onSearchClick = {
                    navController.navigate(NavigationRoutes.CharacterOverview.createRoute(characterName = it))
                },
                viewModel = koinViewModel()
            )
        }

        composable(BottomNavItem.EVENT_SCREEN.route) {
            EventScreen(viewModel = koinViewModel())
        }

        composable(NavigationRoutes.CharacterOverview.route) { backStackEntry ->
            val characterName = backStackEntry.arguments?.getString("characterName") ?: ""
            CharacterOverviewScreenRoot(
                characterName = characterName,
                onBackClick = {
                    navController.navigate(NavigationRoutes.CharacterSearch.route) {
                        popUpTo(NavigationRoutes.CharacterSearch.route) { inclusive = true }
                    }
                },
                viewModel = koinViewModel())
        }

        composable(NavigationRoutes.CharacterAdd.route) { backStackEntry ->
            CharacterAddScreenRoot(
                onBackClick = {
                    navController.navigate(NavigationRoutes.CharacterManager.route) {
                        popUpTo(NavigationRoutes.CharacterManager.route) { inclusive = true }
                    }
                },
                viewModel = koinViewModel()
            )
        }
    }
}
