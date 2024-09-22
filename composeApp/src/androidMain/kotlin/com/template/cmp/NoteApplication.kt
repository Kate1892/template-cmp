package com.template.cmp

import android.app.Application
import android.os.LocaleList
import com.template.cmp.di.initKoin
import org.koin.android.ext.android.get
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class NoteApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@NoteApplication)
        }
//        initializeNetwork()
    }

//    private fun initializeNetwork() {
//        get<NetworkService>().run {
//            initializeNetwork(
//                isProduction = !BuildConfig.isDevEnvironment,
//                isLoggingEnabled = BuildConfig.DEBUG || BuildConfig.isDevEnvironment,
//                deviceLanguage = LocaleList.getDefault()[0].language,
//            )
//        }
//    }
}