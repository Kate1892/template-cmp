package com.template.cmp.features.splash.mvvm

import androidx.lifecycle.viewModelScope
import com.template.cmp.common.mvvm.BaseViewModel
import com.template.cmp.common.mvvm.UiEvent
import com.template.cmp.navigation.NavigationAction
import com.template.cmp.network.features.auth.AuthRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.getString
import org.koin.mp.KoinPlatformTools
import template_cmp.composeapp.generated.resources.Res
import template_cmp.composeapp.generated.resources.scr_splash_screen_title

class SplashViewModel : BaseViewModel<UiEvent, SplashState>(SplashState()) {
    override fun processUiEvent(event: UiEvent) = Unit

    private val authRepository by KoinPlatformTools.defaultContext().get().inject<AuthRepository>()

    init {
        viewModelScope.launch {
            @Suppress("MagicNumber") delay(2000L)
            navigate(NavigationAction.NavigateToLogin)
        }

        funForTestKtor()
    }

    private fun funForTestKtor() {
        viewModelScope.launch {
            authRepository.auth()
            authRepository.addPerson()
        }
    }

    override fun initScreenStrings(): suspend SplashState.() -> SplashState = {
        copy(
            screenTitle = getString(Res.string.scr_splash_screen_title),
        )
    }
}