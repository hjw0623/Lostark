package com.hjw0623.data.di

import com.hjw0623.character.domain.CharacterRepository
import com.hjw0623.character.domain.CharacterSearchPreferences
import com.hjw0623.data.CharacterRepositoryImpl
import com.hjw0623.data.CharacterSearchPreferencesImpl
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val characterDataModule = module {
    singleOf(::CharacterRepositoryImpl).bind<CharacterRepository>()
    single<CharacterSearchPreferences> { CharacterSearchPreferencesImpl(androidContext()) }
}