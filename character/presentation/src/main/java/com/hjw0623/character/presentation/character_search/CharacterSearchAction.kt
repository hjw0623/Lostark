package com.hjw0623.character.presentation.character_search

sealed interface CharacterSearchAction {
    data object ClearSearchQuery : CharacterSearchAction
    data class DeleteSearchHistory(val historyInput: String) : CharacterSearchAction
    data class OnSearchClick(val searchInput: String) : CharacterSearchAction
    data class OnHistoryClick(val historyInput: String) : CharacterSearchAction
}