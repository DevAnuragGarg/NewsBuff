package com.intact.newsbuff

import android.app.Application
import timber.log.Timber

const val API_KEY = "e301baa7786f49fb9a2fe1b1d7f9e77a"

class NewsBuffApp : Application() {

    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}