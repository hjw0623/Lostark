package com.hjw0623.character.presentation.character_manager

sealed interface ManagerAction {
    data object OnCharacterAddClick: ManagerAction
    data object OnCharacterDeleteClick: ManagerAction
    data object OnCharacterSettingClick: ManagerAction
}