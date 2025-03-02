package com.hjw0623.character.presentation.character_add

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hjw0623.character.domain.CharacterRepository
import com.hjw0623.character.presentation.character_add.model.CharacterAddUi
import com.hjw0623.character.presentation.character_add.model.toRoomCharacter
import com.hjw0623.character.presentation.character_overview.model.siblings.toCharacterAddUi
import com.hjw0623.core.domain.character.LocalCharacterDataSource
import com.hjw0623.core.domain.util.onError
import com.hjw0623.core.domain.util.onSuccess
import com.hjw0623.core.presentation.ui.UiText
import com.hjw0623.core.presentation.ui.asUiText
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import timber.log.Timber

class CharacterAddViewModel(
    private val characterRepository: CharacterRepository,
    private val localCharacterDataSource: LocalCharacterDataSource
): ViewModel() {

    private val _state = MutableStateFlow(CharacterAddState())
    val state: StateFlow<CharacterAddState> = _state

    private val _events = Channel<CharacterAddEvent>(Channel.BUFFERED)
    val events = _events.receiveAsFlow()

    private var hasErrorOccurred = false

    fun onAction(action: CharacterAddAction) {
        when (action) {
            is CharacterAddAction.OnSearchClick -> {
                _state.update { it.copy(searchQuery = action.characterName) }
                getCharacterList()
            }
            is CharacterAddAction.OnShowDialog -> {
                _state.update {
                    it.copy(
                        showDialog = true,
                        selectedCharacter = action.character
                    )
                }
            }
            CharacterAddAction.OnDismissAddClick -> {
                _state.update {
                    it.copy(
                        showDialog = false,
                        selectedCharacter = null
                    )
                }
            }
            is CharacterAddAction.OnAddCharacterClick -> {
                addCharacterToLocalDatabase(action.character)

                _state.update {
                    it.copy(
                        showDialog = false,
                        selectedCharacter = null
                    )
                }
            }
            CharacterAddAction.OnClearQueryClick -> {
                _state.update { it.copy(searchQuery = "") }
            }
            CharacterAddAction.OnBackClick -> {
                viewModelScope.launch {
                    _events.send(CharacterAddEvent.NavigateBack)
                }
            }
        }
    }

    private fun addCharacterToLocalDatabase(character: CharacterAddUi) {
        viewModelScope.launch {
            val result =
                localCharacterDataSource.upsertCharacter(character.toRoomCharacter())
            result.onSuccess {
                _events.send(CharacterAddEvent.AddCharacter(character))
            }.onError { error ->
                sendError(error.asUiText())
            }
        }
    }

    private fun getCharacterList() {
        viewModelScope.launch {
            _state.value = _state.value.copy(
                isCharacterListLoading = true
            )
            characterRepository.getSibling(state.value.searchQuery)
                .onSuccess { characterList ->
                    val searchCharacter = characterList.find { character ->
                        character.characterName == state.value.searchQuery
                    }
                    val filteredCharacterList = characterList.filterNot { character ->
                        character.characterName == state.value.searchQuery
                    }

                    _state.update {
                        it.copy(
                            isCharacterListLoading = false,
                            searchCharacter = searchCharacter!!.toCharacterAddUi(),
                            characterList = filteredCharacterList.map { it.toCharacterAddUi() }
                        )
                    }
                    Timber.d("Successfully loaded character list: $characterList")
                }
                .onError { error ->
                    Timber.e("$error", "Failed to load character list")
                    sendError(error.asUiText())
                    _state.update { it.copy(searchQuery = "") }
                }

        }
    }

    private fun sendError(message: UiText) {
        viewModelScope.launch {
            if (!hasErrorOccurred) {
                hasErrorOccurred = true
                _events.send(CharacterAddEvent.Error(message))
            }
        }
    }
}