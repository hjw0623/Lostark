package com.hjw0623.core.data.di


import com.hjw0623.core.data.networking.HttpClientFactory
import io.ktor.client.engine.cio.CIO
import org.koin.dsl.module

val coreDataModule = module {
    single { HttpClientFactory.create(CIO.create()) }
}