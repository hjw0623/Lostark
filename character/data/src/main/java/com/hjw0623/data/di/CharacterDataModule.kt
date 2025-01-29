package com.hjw0623.data.di

import com.hjw0623.character.domain.CharacterRepository
import com.hjw0623.data.CharacterRepositoryImpl
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val characterDataModule = module {
    singleOf(::CharacterRepositoryImpl).bind<CharacterRepository>()
}