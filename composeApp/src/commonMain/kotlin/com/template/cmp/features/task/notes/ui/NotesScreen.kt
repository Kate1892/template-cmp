package com.template.cmp.features.task.notes.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.imePadding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.template.cmp.common.view.BaseScreen
import com.template.cmp.features.task.notes.mvvm.NotesState
import com.template.cmp.features.task.notes.mvvm.NotesUiEvent
import com.template.cmp.features.task.notes.mvvm.NotesViewModel
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI

@OptIn(KoinExperimentalAPI::class)
@Composable
fun NotesScreen(
    viewModel: NotesViewModel = koinViewModel(),
) {
    val state by viewModel.stateFlow.collectAsState()

    BaseScreen {
        NotesScreenView(
            state = state.state,
            onUiEvent = viewModel::onUiEvent,
        )
    }

}

@Composable
fun NotesScreenView(
    state: NotesState,
    onUiEvent: (NotesUiEvent) -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .imePadding(),
    ) {
        Text(text = "Hello, world")
        Button(
            onClick = {
                onUiEvent(NotesUiEvent.OnProfileButtonClicked)
            },
        ) {
            Text("Profile")
        }
    }
}

@Preview
@Composable
private fun PreviewNotesScreenView() {
    val state = NotesState()

    MaterialTheme {
        NotesScreenView(
            state = state,
            onUiEvent = {},
        )
    }
}
