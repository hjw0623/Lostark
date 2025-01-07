package com.hjw0623.lostark

import android.app.Application
import com.hjw0623.core.data.di.coreDataModule
import com.hjw0623.events.data.di.eventsDataModule
import com.hjw0623.events.presentation.events.di.eventsPresentationModule
import com.hjw0623.lostark.di.appModule
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.androidx.workmanager.koin.workManagerFactory
import org.koin.core.context.startKoin
import timber.log.Timber

class LostArkApp: Application() {

    val applicationScope = CoroutineScope(SupervisorJob())

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }

        startKoin {
            androidLogger()
            androidContext(this@LostArkApp)
            workManagerFactory()
            modules(
                appModule,
                coreDataModule,
                eventsDataModule,
                eventsPresentationModule
            )
        }
    }
}