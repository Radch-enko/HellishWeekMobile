package com.blesscompany.hellishweek.mobile.android

import android.app.Application
import com.blesscompany.hellishweek.injector.Injector
import com.blesscompany.hellishweek.mobile.android.di.viewModelsModule
import timber.log.Timber
import com.blesscompany.hellishweek.common.expectation.Application as ApplicationShared

class HellishWeekApp : Application() {

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
        Injector.initKoin(application = ApplicationShared(this), modules = listOf(viewModelsModule))
    }
}