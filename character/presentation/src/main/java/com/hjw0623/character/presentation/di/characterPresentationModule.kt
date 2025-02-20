package com.hjw0623.character.presentation.di

import com.hjw0623.character.presentation.character_overview.CharacterViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val characterPresentationModule = module {
    viewModelOf(::CharacterViewModel)
}