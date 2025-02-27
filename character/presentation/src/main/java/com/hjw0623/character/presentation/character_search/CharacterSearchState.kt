package com.hjw0623.character.presentation.character_search

data class CharacterSearchState(
    val isSearching: Boolean = false,
    val searchHistory: List<String> = emptyList(),
    val searchQuery: String = ""
)
