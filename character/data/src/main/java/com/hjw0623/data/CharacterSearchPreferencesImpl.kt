package com.hjw0623.data

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringSetPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.hjw0623.character.domain.CharacterSearchPreferences
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext

private val Context.dataStore by preferencesDataStore(name = "search_history")

class CharacterSearchPreferencesImpl(
    private val context: Context
) : CharacterSearchPreferences {

    private val SEARCH_HISTORY_KEY = stringSetPreferencesKey("search_history")

    override val searchHistory: Flow<List<String>> = context.dataStore.data
        .map { preferences ->
            preferences[SEARCH_HISTORY_KEY]?.toList()?.sortedDescending() ?: emptyList()
        }
        .flowOn(Dispatchers.IO)

    override suspend fun addSearchHistory(searchInput: String) {
        withContext(Dispatchers.IO) {
            context.dataStore.edit { preferences ->
                val currentHistory = preferences[SEARCH_HISTORY_KEY]?.toMutableList() ?: mutableListOf()
                currentHistory.remove(searchInput)
                currentHistory.add(0, searchInput)
                if (currentHistory.size > 10) { // 🔥
                    currentHistory.removeAt(currentHistory.lastIndex)
                }
                preferences[SEARCH_HISTORY_KEY] = currentHistory.toSet()
            }
        }
    }

    override suspend fun clearSearchHistory(historyInput: String) {
        withContext(Dispatchers.IO) {
            context.dataStore.edit { preferences ->
                val currentHistory = preferences[SEARCH_HISTORY_KEY]?.toMutableList() ?: mutableListOf()
                currentHistory.remove(historyInput)
                preferences[SEARCH_HISTORY_KEY] = currentHistory.toSet()
            }
        }
    }

}