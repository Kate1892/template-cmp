package com.template.cmp.features.auth.login.mvvm

import com.template.cmp.common.mvvm.BaseViewModel
import com.template.cmp.navigation.NavigationAction
import org.jetbrains.compose.resources.getString
import template_cmp.composeapp.generated.resources.Res
import template_cmp.composeapp.generated.resources.scr_login_continue_button_title
import template_cmp.composeapp.generated.resources.scr_login_password_placeholder
import template_cmp.composeapp.generated.resources.scr_login_screen_description
import template_cmp.composeapp.generated.resources.scr_login_username_placeholder
import template_cmp.composeapp.generated.resources.scr_splash_screen_title

class LoginViewModel : BaseViewModel<LoginUiEvent, LoginState>(LoginState()) {

    override fun processUiEvent(event: LoginUiEvent) {
        when (event) {
            LoginUiEvent.OnContinueButtonClicked -> {
                navigate(NavigationAction.NavigateToCheckPermission)
            }

            is LoginUiEvent.OnEmailFieldFocusChanged -> {

            }

            is LoginUiEvent.OnEmailFieldValueChanged -> {

            }
        }
    }

    override fun initScreenStrings(): suspend LoginState.() -> LoginState = {
        copy(
            screenTitle = getString(Res.string.scr_splash_screen_title),
            screenDescription = getString(Res.string.scr_login_screen_description),
            continueButtonTitle = getString(
                (Res.string.scr_login_continue_button_title),
            ),
            userNameFieldState = userNameFieldState.copy(
                placeholder = getString(
                    Res.string.scr_login_username_placeholder,
                ),
            ),
            passwordFieldState = userNameFieldState.copy(
                placeholder = getString(
                    Res.string.scr_login_password_placeholder,
                )
            )
        )
    }

}