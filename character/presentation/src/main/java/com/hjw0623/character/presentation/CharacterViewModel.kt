package com.hjw0623.character.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hjw0623.character.domain.CharacterRepository
import com.hjw0623.character.domain.model.gear.Gear
import com.hjw0623.character.presentation.model.gear.categorizeGears
import com.hjw0623.character.presentation.model.gear.toAbilityStoneUi
import com.hjw0623.character.presentation.model.gear.toAccessoriesUi
import com.hjw0623.character.presentation.model.gear.toGearUi
import com.hjw0623.character.presentation.model.profile.toCharacterProfileUi
import com.hjw0623.core.domain.util.onError
import com.hjw0623.core.domain.util.onSuccess
import com.hjw0623.core.presentation.ui.UiText
import com.hjw0623.core.presentation.ui.asUiText
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.koin.androidx.compose.get
import timber.log.Timber

class CharacterViewModel(
    private val characterRepository: CharacterRepository
) : ViewModel() {

    private val _state = MutableStateFlow(CharacterState())
    var state = _state
        .onStart { loadAllData() }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000L),
            CharacterState()
        )

    private val _events = Channel<CharacterEvent>(Channel.BUFFERED)
    val events = _events.receiveAsFlow()

    private var hasErrorOccurred = false

    private fun loadAllData() {
        loadCharacterProfile()
        loadGear()
    }

    private fun loadCharacterProfile() {
        viewModelScope.launch {
            _state.update { it.copy(isCharacterProfileLoading = true) }

            characterRepository
                .getCharacterProfile(state.value.searchedCharacterName)
                .onSuccess { profile ->
                    _state.update {
                        it.copy(
                            isCharacterProfileLoading = false,
                            characterProfile = profile.toCharacterProfileUi()
                        )
                    }
                    Timber.d("Successfully loaded characterProfile: $profile")
                }
                .onError { error ->
                    Timber.e("$error", "Failed to load characterProfile")
                    sendError(error.asUiText())
                }
        }
    }

    private fun loadGear() {
        viewModelScope.launch {
            _state.update { it.copy(isGearLoading = true) }
            characterRepository.getGear(state.value.searchedCharacterName)
                .onSuccess { gearList ->
                    val categorizedGears = categorizeGears(gearList)
                    val onlyEquipment = categorizedGears["장비"] ?: emptyList()
                    val onlyAccessories = categorizedGears["장신구"] ?: emptyList()
                    val onlyAbilityStone = categorizedGears["어빌리티 스톤"] ?: emptyList()
                    _state.update {
                        it.copy(
                            isGearLoading = false,
                            gearList = onlyEquipment.map { it.toGearUi() },
                            accessoriesList = onlyAccessories.map { it.toAccessoriesUi() },
                            abilityStone = onlyAbilityStone.map { it.toAbilityStoneUi() }
                        )
                    }
                    Timber.d("Successfully loaded characterProfile: $gearList")
                }
        }
    }

    private fun sendError(message: UiText) {
        viewModelScope.launch {
            if (!hasErrorOccurred) {
                hasErrorOccurred = true
                _events.send(CharacterEvent.Error(message))
            }
        }
    }


}