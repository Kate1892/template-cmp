package com.template.cmp.features.auth.login.mvvm

import com.template.cmp.common.mvvm.SingleClickUiEvent
import com.template.cmp.common.mvvm.UiEvent

sealed class LoginUiEvent : UiEvent {
    data object OnContinueButtonClicked : LoginUiEvent(), SingleClickUiEvent
    class OnEmailFieldFocusChanged(val focused: Boolean) : LoginUiEvent()
    class OnEmailFieldValueChanged(val value: String) : LoginUiEvent()
}