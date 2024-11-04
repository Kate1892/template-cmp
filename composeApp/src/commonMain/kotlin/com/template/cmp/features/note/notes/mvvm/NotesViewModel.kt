package com.template.cmp.features.note.notes.mvvm

import androidx.lifecycle.viewModelScope
import com.template.cmp.common.mvvm.BaseViewModel
import com.template.cmp.network.features.auth.AuthRepository
import kotlinx.coroutines.launch
import org.koin.mp.KoinPlatformTools

class NotesViewModel : BaseViewModel<NotesUiEvent, NotesState>(NotesState()) {

    private val authRepository
            by KoinPlatformTools.defaultContext().get().inject<AuthRepository>()


    override fun processUiEvent(event: NotesUiEvent) {
        when (event) {
            NotesUiEvent.OnProfileButtonClicked -> {
                viewModelScope.launch {
//                    when (authRepository.auth()) {
//
//                    }
                }
            }
        }
    }
}