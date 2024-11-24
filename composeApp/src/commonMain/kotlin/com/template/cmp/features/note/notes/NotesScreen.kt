package com.template.cmp.features.note.notes

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.windowInsetsTopHeight
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.template.cmp.common.view.BaseScreen
import com.template.cmp.features.common.compose.view.NotePrimaryButton
import com.template.cmp.features.common.compose.view.NoteText
import com.template.cmp.features.common.compose.view.VSpacer
import com.template.cmp.features.note.notes.mvvm.NotesState
import com.template.cmp.features.note.notes.mvvm.NotesUiEvent
import com.template.cmp.features.note.notes.mvvm.NotesViewModel
import com.template.cmp.navigation.NavigationAction
import com.template.cmp.theme.NoteTheme
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
        onDefaultUiEvent = viewModel::onDefaultUiEvent,
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
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Spacer(
            Modifier
                .windowInsetsTopHeight(WindowInsets.statusBars)
                .background(androidx.compose.ui.graphics.Color.Transparent)
        )
        NoteText(
            text = state.text,
            color = NoteTheme.colors.textAccent,
        )
        VSpacer(24.dp)
        Image(
            painter = painterResource(Res.drawable.ic_student),
            contentDescription = "",
            modifier = Modifier.size(100.dp),
        )
        VSpacer(24.dp)

        NotePrimaryButton(
            text = state.logoutButtonTitle,
            modifier = Modifier.padding(horizontal = 16.dp),
            onClick = {
                onUiEvent(NotesUiEvent.OnLogoutButtonClicked)
            },
        )
    }
}

/** Preview for now available only in androidMain */
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
