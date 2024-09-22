package com.template.cmp

import androidx.compose.ui.window.ComposeUIViewController
import com.template.cmp.di.initKoin

fun MainViewController() = ComposeUIViewController(
    configure = {
        initKoin()
    }
) {
    App()
}