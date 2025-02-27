package com.hjw0623.character.domain

import kotlinx.coroutines.flow.Flow


interface CharacterSearchPreferences {
    val searchHistory: Flow<List<String>>
    suspend fun addSearchHistory(searchInput: String)
    suspend fun clearSearchHistory(historyInput: String)
}