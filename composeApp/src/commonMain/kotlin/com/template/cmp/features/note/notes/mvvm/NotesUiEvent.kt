package com.template.cmp.features.note.notes.mvvm

import com.template.cmp.common.mvvm.SingleClickUiEvent
import com.template.cmp.common.mvvm.UiEvent

sealed class NotesUiEvent : UiEvent {
    data object OnLogoutButtonClicked : NotesUiEvent(), SingleClickUiEvent
}