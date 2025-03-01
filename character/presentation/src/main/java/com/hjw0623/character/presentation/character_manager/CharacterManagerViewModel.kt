package com.hjw0623.character.presentation.character_manager

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.hjw0623.character.domain.CharacterRepository
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow


class CharacterManagerViewModel(
    private val characterRepository: CharacterRepository
) : ViewModel() {

    var state by mutableStateOf(CharacterManagerState())
        private set

    private val eventChannel = Channel<CharacterManagerEvent>()
    val events = eventChannel.receiveAsFlow()



}
