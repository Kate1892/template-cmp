package com.template.cmp

import android.app.Application
import com.template.cmp.di.initKoin
import org.koin.android.ext.koin.androidContext

class NoteApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        initKoin {
            androidContext(this@NoteApplication)
        }
    }
}