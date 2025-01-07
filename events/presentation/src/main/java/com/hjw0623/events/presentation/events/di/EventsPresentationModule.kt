package com.hjw0623.events.presentation.events.di

import com.hjw0623.events.presentation.events.EventsViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val eventsPresentationModule = module {
    viewModelOf(::EventsViewModel)
}