package com.hjw0623.character.presentation.character_add

import com.hjw0623.character.presentation.character_add.model.CharacterAddUi

data class CharacterAddState(
    val isCharacterListLoading: Boolean = false,
    val searchQuery: String = "",
    val searchCharacter: CharacterAddUi? = null,
    val characterList: List<CharacterAddUi> = emptyList(),
    val showDialog: Boolean = false,
    val selectedCharacter: CharacterAddUi? = null
)
