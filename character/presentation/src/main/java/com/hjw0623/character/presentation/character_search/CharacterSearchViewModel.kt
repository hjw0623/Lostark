package com.hjw0623.character.presentation.character_search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hjw0623.character.domain.CharacterSearchPreferences
import com.hjw0623.core.presentation.ui.UiText
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class CharacterSearchViewModel(
    private val preferences: CharacterSearchPreferences
) : ViewModel() {
    private val _state = MutableStateFlow(CharacterSearchState())
    val state = _state.asStateFlow()

    private val _events = Channel<CharacterSearchEvent>(Channel.BUFFERED)
    val events = _events.receiveAsFlow()

    init {
        observeSearchHistory()
    }


    private fun observeSearchHistory() {
        viewModelScope.launch {
            preferences.searchHistory.collect { history ->
                _state.update { it.copy(searchHistory = history) }
            }
        }
    }

    fun onAction(action: CharacterSearchAction) {
        when (action) {
            is CharacterSearchAction.OnSearchClick -> {
                val searchQuery = action.searchInput.trim()
                if (searchQuery.isNotBlank()) {
                    viewModelScope.launch {
                        preferences.addSearchHistory(searchQuery)
                        _state.update { it.copy(searchQuery = searchQuery) }
                        _events.send(CharacterSearchEvent.NavigationToCharacterOverviewScreen(searchQuery))
                    }
                } else {
                    viewModelScope.launch {
                        _events.send(CharacterSearchEvent.Error(UiText.DynamicString("검색어를 입력해 주세요")))
                    }
                }
            }

            is CharacterSearchAction.OnHistoryClick -> {
                val historyQuery = action.historyInput.trim()
                if (historyQuery.isNotBlank()) {
                    _state.update { it.copy(searchQuery = historyQuery) }
                }
            }

            CharacterSearchAction.ClearSearchQuery -> {
                _state.update { it.copy(searchQuery = "") }
            }

            is CharacterSearchAction.DeleteSearchHistory -> {
                viewModelScope.launch {
                    preferences.clearSearchHistory(action.historyInput)
                }
            }
        }
    }
}

