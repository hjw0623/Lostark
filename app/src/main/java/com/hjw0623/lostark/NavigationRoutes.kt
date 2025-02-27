package com.hjw0623.lostark

sealed class NavigationRoutes(val route: String) {
    data object CharacterManager : NavigationRoutes("character_manager")
    data object CharacterSearch : NavigationRoutes("character_search")
    data object Event : NavigationRoutes("event")
    data object CharacterScreen : NavigationRoutes("character_screen/{characterName}") {
        fun createRoute(characterName: String) = "character_screen/$characterName"
    }
}
