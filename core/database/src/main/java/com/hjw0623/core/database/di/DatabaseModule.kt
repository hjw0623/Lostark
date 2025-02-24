package com.hjw0623.core.database.di

import androidx.room.Room
import com.hjw0623.core.database.CharacterDataBase
import com.hjw0623.core.database.RoomLocalCharacterDataSource
import com.hjw0623.core.domain.character.LocalCharacterDataSource
import org.koin.android.ext.koin.androidApplication
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val databaseModule = module {
    single {
        Room.databaseBuilder(
            androidApplication(),
            CharacterDataBase::class.java,
            "character.db"
        ).build()
    }

    single { get<CharacterDataBase>().characterDao() }
    single { get<CharacterDataBase>().selectedRaidDao() }

    singleOf(::RoomLocalCharacterDataSource).bind<LocalCharacterDataSource>()
}
