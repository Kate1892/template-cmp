package com.template.cmp.common.mvvm

interface UiEvent

interface SingleClickUiEvent : UiEvent

/** Default ui events */
sealed class DefaultUiEvent : UiEvent {
    data object OnScreenCreated : DefaultUiEvent()
    data object OnScreenResumed : DefaultUiEvent()
    data object OnScreenDestroyed : DefaultUiEvent()
    data object OnBackClicked : DefaultUiEvent(), SingleClickUiEvent
}
