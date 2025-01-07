package com.hjw0623.events.data.di

import com.hjw0623.events.data.EventsRepositoryImpl
import com.hjw0623.events.domain.EventsRepository
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val eventsDataModule = module {
    singleOf(::EventsRepositoryImpl).bind<EventsRepository>()
}