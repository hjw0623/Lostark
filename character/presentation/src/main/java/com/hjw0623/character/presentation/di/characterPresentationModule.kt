package com.hjw0623.character.presentation.di

import com.hjw0623.character.presentation.character_add.CharacterAddViewModel
import com.hjw0623.character.presentation.character_manager.CharacterManagerViewModel
import com.hjw0623.character.presentation.character_overview.CharacterOverviewViewModel
import com.hjw0623.character.presentation.character_search.CharacterSearchViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val characterPresentationModule = module {
    viewModelOf(::CharacterOverviewViewModel)
    viewModelOf(::CharacterManagerViewModel)
    viewModelOf(::CharacterSearchViewModel)
    viewModelOf(::CharacterAddViewModel)
}