package com.template.cmp.features.auth.login.mvvm

import com.template.cmp.common.view.TextFieldState

data class LoginState(
    val screenTitle: String = "",
    val screenDescription: String = "",
    val continueButtonTitle: String = "",
    val isContinueButtonEnabled: Boolean = false,
    val userNameFieldState: TextFieldState = TextFieldState(),
    val passwordFieldState: TextFieldState = TextFieldState(),
    )