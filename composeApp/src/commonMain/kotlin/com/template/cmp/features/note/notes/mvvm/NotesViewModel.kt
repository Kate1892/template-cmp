package com.template.cmp.features.note.notes.mvvm

import com.template.cmp.common.mvvm.BaseViewModel
import com.template.cmp.navigation.NavigationAction
import org.jetbrains.compose.resources.getString
import template_cmp.composeapp.generated.resources.Res
import template_cmp.composeapp.generated.resources.scr_notes_logout_button_title
import template_cmp.composeapp.generated.resources.scr_notes_screen_title

class NotesViewModel : BaseViewModel<NotesUiEvent, NotesState>(NotesState()) {

    override fun processUiEvent(event: NotesUiEvent) {
        when (event) {
            NotesUiEvent.OnLogoutButtonClicked -> {
                navigate(NavigationAction.NavigateBackToLogin())
            }
        }
    }

    override fun initScreenStrings(): suspend NotesState.() -> NotesState = {
        copy(
            text = getString(Res.string.scr_notes_screen_title),
            logoutButtonTitle = getString(Res.string.scr_notes_logout_button_title)
        )
    }
}