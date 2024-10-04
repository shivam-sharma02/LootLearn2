package com.fourtysix.lootlearn

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
class LootLearnApplication : Application() {
    override fun onCreate() {
        super.onCreate()
//        if(BuildConfig.DEBUG){
//            Timber.plant(Timber.DebugTree())
//        }
        Timber.plant(Timber.DebugTree())
    }
}