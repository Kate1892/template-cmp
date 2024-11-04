package com.template.cmp.features.auth.login

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.template.cmp.common.view.BaseScreen
import com.template.cmp.features.auth.login.mvvm.LoginState
import com.template.cmp.features.auth.login.mvvm.LoginUiEvent
import com.template.cmp.features.auth.login.mvvm.LoginViewModel
import com.template.cmp.features.common.compose.view.NotePrimaryButton
import com.template.cmp.features.common.compose.view.NoteText
import com.template.cmp.features.common.compose.view.NoteTextField
import com.template.cmp.features.common.compose.view.VSpacer
import com.template.cmp.navigation.NavigationAction
import com.template.cmp.theme.NoteTheme
import com.template.cmp.theme.NoteTypography
import org.koin.compose.viewmodel.koinViewModel

fun NavGraphBuilder.navRouteLogin() {
    composable<NavigationAction.NavigateToLogin> {
        LoginScreen()
    }
}

@Composable
fun LoginScreen(
    viewModel: LoginViewModel = koinViewModel()
) {
    val state by viewModel.stateFlow.collectAsState()

    BaseScreen(
        lceState = state.lceState,
    ) {
        LoginScreenView(
            state = state.state,
            onUiEvent = viewModel::onUiEvent,
        )
    }
}

@Composable
private fun LoginScreenView(
    state: LoginState,
    onUiEvent: (LoginUiEvent) -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .imePadding()
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .weight(1f),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            NoteText(
                text = state.screenTitle,
                style = NoteTypography().titleLarge,
            )
            VSpacer(16.dp)
            NoteText(
                text = state.screenDescription,
                style = NoteTypography().titleSmall,
                color = NoteTheme.colors.textPrimary,
            )
            VSpacer(16.dp)
            NoteTextField(
                fieldState = state.emailFieldState,
                onValueChange = { },
                onFocusChange = { },
            )
        }
        NotePrimaryButton(
            modifier = Modifier.padding(16.dp),
            text = state.continueButtonTitle,
//            isEnabled = state.isContinueButtonEnabled,
            onClick = { onUiEvent(LoginUiEvent.OnContinueButtonClicked) },
        )
    }
}