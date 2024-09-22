package com.template.cmp.features.task.notes.mvvm

import com.template.cmp.common.mvvm.SingleClickUiEvent
import com.template.cmp.common.mvvm.UiEvent

sealed class NotesUiEvent : UiEvent {
    data object OnProfileButtonClicked : NotesUiEvent(), SingleClickUiEvent
}