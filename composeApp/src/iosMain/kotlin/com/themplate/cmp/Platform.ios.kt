package com.themplate.cmp

import platform.UIKit.UIDevice
import io.github.alexzhirkevich.cupertino.adaptive.Theme

class IOSPlatform: Platform {
    override val name: String = UIDevice.currentDevice.systemName() + " " + UIDevice.currentDevice.systemVersion
}

actual fun getPlatform(): Platform = IOSPlatform()

actual fun determineTheme(): Theme = Theme.Cupertino