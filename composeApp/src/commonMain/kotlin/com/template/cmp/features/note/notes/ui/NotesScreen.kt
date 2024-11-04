package com.template.cmp.features.note.notes.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.template.cmp.common.view.BaseScreen
import com.template.cmp.features.note.notes.mvvm.NotesState
import com.template.cmp.features.note.notes.mvvm.NotesUiEvent
import com.template.cmp.features.note.notes.mvvm.NotesViewModel
import com.template.cmp.navigation.NavigationAction
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.viewmodel.koinViewModel
import template_cmp.composeapp.generated.resources.Res
import template_cmp.composeapp.generated.resources.ic_student

fun NavGraphBuilder.navRouteNotes() {
    composable<NavigationAction.NavigateToNotes> {
        NotesScreen()
    }
}

@Composable
fun NotesScreen(
    viewModel: NotesViewModel = koinViewModel(),
) {
    val state by viewModel.stateFlow.collectAsState()

    BaseScreen(
        lceState = state.lceState,
    ) {
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

        Image(
            painter = painterResource(Res.drawable.ic_student),
            contentDescription = "",
            modifier = Modifier.size(100.dp),
        )
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
