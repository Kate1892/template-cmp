package com.template.cmp.features.checkPermission.mvvm

import com.template.cmp.common.mvvm.SingleClickUiEvent
import com.template.cmp.common.mvvm.UiEvent

sealed class CheckPermissionUiEvent : UiEvent {
    data object OnScreenDrawn : CheckPermissionUiEvent(), SingleClickUiEvent
    data object OnRequestPermissionButtonClicked : CheckPermissionUiEvent(), SingleClickUiEvent
    data object OnGoToSettingsButtonClicked : CheckPermissionUiEvent(), SingleClickUiEvent
    data object OnContinueButtonClicked : CheckPermissionUiEvent(), SingleClickUiEvent
}
