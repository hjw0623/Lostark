package com.hjw0623.character.presentation.character_overview

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hjw0623.character.domain.CharacterRepository
import com.hjw0623.character.presentation.character_overview.mockup.emptyAbilityStoneUi
import com.hjw0623.character.presentation.character_overview.mockup.emptyBraceletUi
import com.hjw0623.character.presentation.character_overview.model.arkpassive.toArkPassiveUi
import com.hjw0623.character.presentation.character_overview.model.avatar.toAvatarUi
import com.hjw0623.character.presentation.character_overview.model.card.toCardEffectUi
import com.hjw0623.character.presentation.character_overview.model.card.toCardUi
import com.hjw0623.character.presentation.character_overview.model.collectibles.toCollectibleSummationUi
import com.hjw0623.character.presentation.character_overview.model.engraving.toEngravingUi
import com.hjw0623.character.presentation.character_overview.model.gear.ElixirUi
import com.hjw0623.character.presentation.character_overview.model.gear.TranscendenceUi
import com.hjw0623.character.presentation.character_overview.model.gear.categorizeGears
import com.hjw0623.character.presentation.character_overview.model.gear.toAbilityStoneUi
import com.hjw0623.character.presentation.character_overview.model.gear.toAccessoriesUi
import com.hjw0623.character.presentation.character_overview.model.gear.toBraceletUi
import com.hjw0623.character.presentation.character_overview.model.gear.toGearUi
import com.hjw0623.character.presentation.character_overview.model.gear.toGemsUi
import com.hjw0623.character.presentation.character_overview.model.profile.toCharacterProfileUi
import com.hjw0623.character.presentation.character_overview.model.profile.toCharacterStatsUi
import com.hjw0623.character.presentation.character_overview.model.siblings.toSiblingUi
import com.hjw0623.character.presentation.character_overview.model.skill.toSkillUi
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

class CharacterOverviewViewModel(
    private val characterRepository: CharacterRepository
) : ViewModel() {

    private val _state = MutableStateFlow(CharacterOverviewState())
    val state: StateFlow<CharacterOverviewState> = _state

    fun setCharacterName(characterName: String) {
        viewModelScope.launch {
            _state.update { it.copy(searchedCharacterName = characterName) }
            loadAllData()
        }
    }


    private val _events = Channel<CharacterOverviewEvent>(Channel.BUFFERED)
    val events = _events.receiveAsFlow()


    private var hasErrorOccurred = false

    private fun loadAllData() {
        loadCharacterProfile()
        loadGear()
        loadGem()
        loadEngraving()
        loadCard()
        loadArkPassive()
        loadSkill()
        loadAvatar()
        loadSibling()
        loadCollectible()
    }

    private fun loadCharacterProfile() {
        viewModelScope.launch {
            _state.update { it.copy(isCharacterProfileLoading = true) }

            characterRepository
                .getCharacterProfile(state.value.searchedCharacterName)
                .onSuccess { profile ->
                    val stats = profile.toCharacterStatsUi()
                    _state.update {
                        it.copy(
                            isCharacterProfileLoading = false,
                            characterProfile = profile.toCharacterProfileUi(),
                            stats = stats
                        )
                    }
                }
                .onError { error ->
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
                    val onlyBracelet = categorizedGears["팔찌"] ?: emptyList()
                    val gearUiList = onlyEquipment.map { it.toGearUi() }

                    val allElixirs = gearUiList.flatMap { it.elixirList ?: emptyList() }
                        .filter { it.isNotBlank() }
                    val totalElixirSum = gearUiList.sumOf { it.elixirSum }
                    val elixirEffect = findElixirEffect(allElixirs)
                    val effectLevel = when {
                        totalElixirSum >= 40 -> "2단계"
                        totalElixirSum >= 35 -> "1단계"
                        else -> ""
                    }
                    val activeEffect = if (elixirEffect.isNotEmpty() && effectLevel.isNotEmpty()) {
                        "$elixirEffect $effectLevel"
                    } else {
                        ""
                    }
                    val totalTranscendenceSum = gearUiList.sumOf { it.transcendence }
                    val avgTranscendenceGrade =
                        (gearUiList.sumOf { it.transcendenceGrade }.toDouble() / 6.0)
                            .let { String.format("%.1f", it).toDouble() }
                    _state.update {
                        it.copy(
                            isGearLoading = false,
                            gearList = gearUiList,
                            accessoriesList = onlyAccessories.map { it.toAccessoriesUi() },
                            abilityStone = onlyAbilityStone.firstOrNull()?.toAbilityStoneUi()
                                ?: emptyAbilityStoneUi,
                            bracelet = onlyBracelet.firstOrNull()?.toBraceletUi()
                                ?: emptyBraceletUi,
                            elixir = ElixirUi(
                                total = totalElixirSum,
                                activeEffect = activeEffect
                            ),
                            transcendence = TranscendenceUi(
                                total = totalTranscendenceSum,
                                avgLevel = avgTranscendenceGrade
                            )
                        )
                    }
                }

        }
    }

    private fun loadGem() {
        viewModelScope.launch {
            _state.update { it.copy(isGemLoading = true) }
            characterRepository.getGems(state.value.searchedCharacterName)
                .onSuccess { gemsList ->
                    _state.update {
                        it.copy(
                            isGemLoading = false,
                            gemsList = gemsList.gems.map { it.toGemsUi() }
                        )
                    }
                }
        }
    }

    private fun loadEngraving() {
        viewModelScope.launch {
            _state.update { it.copy(isEngravingLoading = true) }

            characterRepository
                .getEngravings(state.value.searchedCharacterName)
                .onSuccess { engravingsList ->
                    _state.update {
                        it.copy(
                            isEngravingLoading = false,
                            engraving = engravingsList.arkPassiveEffect?.map { it.toEngravingUi() }
                                ?: emptyList()
                        )
                    }

                }
                .onError { error ->
                    sendError(error.asUiText())
                }
        }
    }

    private fun loadCard() {
        viewModelScope.launch {
            _state.update { it.copy(isCardLoading = true) }

            characterRepository.getCards(state.value.searchedCharacterName)
                .onSuccess { cardList ->
                    val card = cardList.cards.map { it.toCardUi() }
                    val cardEffect = cardList.cardEffects.map { it.toCardEffectUi() }
                    _state.update {
                        it.copy(
                            isCardLoading = false,
                            card = card,
                            cardEffect = cardEffect
                        )
                    }
                }
                .onError { error ->
                    sendError(error.asUiText())
                }
        }
    }

    private fun loadArkPassive() {
        viewModelScope.launch {
            _state.update { it.copy(isArkPassiveLoading = true) }

            characterRepository.getArkPassive(state.value.searchedCharacterName)
                .onSuccess { arkPassive ->
                    _state.update {
                        it.copy(
                            isArkPassiveLoading = false,
                            arkPassive = arkPassive.toArkPassiveUi()
                        )
                    }
                }
                .onError { error ->
                    sendError(error.asUiText())
                }
        }
    }

    private fun loadSkill() {
        viewModelScope.launch {
            _state.update { it.copy(isSkillLoading = true) }

            characterRepository.getSkill(state.value.searchedCharacterName)
                .onSuccess { skillList ->
                    val filteredSkillList = skillList.filter { skill ->
                        skill.level >= 2 ||
                                state.value.gemsList.any { it.skillName == skill.name } ||
                                skill.rune?.let { it.name.isNotBlank() } == true
                    }

                    _state.update {
                        it.copy(
                            isSkillLoading = false,
                            skillList = filteredSkillList.map { it.toSkillUi() }

                        )
                    }
                }
                .onError { error ->
                    sendError(error.asUiText())
                }
        }
    }

    private fun loadAvatar() {
        viewModelScope.launch {
            _state.update { it.copy(isAvatarLoading = true) }

            characterRepository.getAvatar(state.value.searchedCharacterName)
                .onSuccess { avatarList ->
                    _state.update {
                        it.copy(
                            isAvatarLoading = false,
                            avatarList = avatarList.map { it.toAvatarUi() }
                        )
                    }
                }
                .onError { error ->
                    sendError(error.asUiText())
                }
        }
    }

    private fun loadSibling() {
        viewModelScope.launch {
            _state.update { it.copy(isSiblingLoading = true) }

            characterRepository.getSibling(state.value.searchedCharacterName)
                .onSuccess { siblingList ->
                    _state.update {
                        it.copy(
                            isSiblingLoading = false,
                            siblingList = siblingList.map { it.toSiblingUi() }
                        )
                    }
                }
                .onError { error ->
                    sendError(error.asUiText())
                }
        }
    }

    private fun loadCollectible() {
        viewModelScope.launch {
            _state.update { it.copy(isCollectibleLoading = true) }

            characterRepository.getCollectibles(state.value.searchedCharacterName)
                .onSuccess { collectibleList ->
                    _state.update {
                        it.copy(
                            isCollectibleLoading = false,
                            collectibleSummationList = collectibleList.map { it.toCollectibleSummationUi() }
                        )
                    }
                }
                .onError { error ->
                    sendError(error.asUiText())
                }
        }
    }


    private fun sendError(message: UiText) {
        viewModelScope.launch {
            if (!hasErrorOccurred) {
                hasErrorOccurred = true
                _events.send(CharacterOverviewEvent.Error(message))
                _events.send(CharacterOverviewEvent.NavigateBack)
            }
        }
    }

    private fun findElixirEffect(elixirList: List<String>): String {
        val effectPairs = mapOf(
            "강맹 (질서)" to "강맹 (혼돈)", "달인 (질서)" to "달인 (혼돈)", "선각자 (질서)" to "선각자 (혼돈)",
            "선봉대 (질서)" to "선봉대 (혼돈)", "신념 (질서)" to "신념 (혼돈)", "진군 (질서)" to "진군 (혼돈)",
            "칼날 방패 (질서)" to "칼날 방패 (혼돈)", "행운 (질서)" to "행운 (혼돈)", "회심 (질서)" to "회심 (혼돈)"
        )

        val normalizedElixirs = elixirList.map { it.replace(Regex("\\sLv\\.\\d+"), "").trim() }

        for ((order, chaos) in effectPairs) {
            if (normalizedElixirs.any { it.trim() == order } && normalizedElixirs.any { it.trim() == chaos }) {
                return order.substringBefore(" (")
            }
        }
        return ""
    }

}