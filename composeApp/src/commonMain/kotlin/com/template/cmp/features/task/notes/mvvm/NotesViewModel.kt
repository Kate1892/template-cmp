package com.template.cmp.features.task.notes.mvvm

import com.template.cmp.common.mvvm.BaseViewModel
import com.template.cmp.navigation.NavigationScreen

class NotesViewModel(

) : BaseViewModel<NotesUiEvent, NotesState>(NotesState()) {
    override fun processUiEvent(event: NotesUiEvent) {
        when (event) {
            NotesUiEvent.OnProfileButtonClicked -> {
                navigate(
                    NavigationScreen.Profile(
                        name = "Jess",
                        age = 2,
                    )
                )
            }
        }
    }
}