package com.template.cmp

import android.annotation.SuppressLint
import android.content.pm.ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        @SuppressLint("SourceLockedOrientationActivity")
        requestedOrientation = SCREEN_ORIENTATION_PORTRAIT
        installSplashScreen()
        setContent {
            App()
        }
    }
}
