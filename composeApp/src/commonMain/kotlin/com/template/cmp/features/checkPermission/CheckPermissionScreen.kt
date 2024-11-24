package com.template.cmp.features.checkPermission

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.template.cmp.common.view.BaseScreen
import com.template.cmp.features.checkPermission.mvvm.CheckPermissionState
import com.template.cmp.features.checkPermission.mvvm.CheckPermissionUiEvent
import com.template.cmp.features.checkPermission.mvvm.CheckPermissionViewModel
import com.template.cmp.features.common.compose.view.NotePrimaryButton
import com.template.cmp.features.common.compose.view.NoteText
import com.template.cmp.features.common.compose.view.VSpacer
import com.template.cmp.navigation.NavigationAction
import com.template.cmp.theme.NoteColors
import com.template.cmp.theme.NoteTheme
import dev.icerock.moko.permissions.PermissionState
import dev.icerock.moko.permissions.compose.BindEffect
import dev.icerock.moko.permissions.compose.rememberPermissionsControllerFactory
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.parameter.parametersOf

fun NavGraphBuilder.navRouteCheckPermission() {
    composable<NavigationAction.NavigateToCheckPermission> {
        CheckPermissionScreen()
    }
}

@Composable
fun CheckPermissionScreen() {
    val factory = rememberPermissionsControllerFactory()
    val controller = remember(factory) {
        factory.createPermissionsController()
    }

    BindEffect(controller) // bind ui and vm lifecycles

    val viewModel: CheckPermissionViewModel =
        koinViewModel(parameters = { parametersOf(controller) })

    val state by viewModel.stateFlow.collectAsState()

    BaseScreen(
        lceState = state.lceState,
        onDefaultUiEvent = viewModel::onDefaultUiEvent,
    ) {
        CheckPermissionScreenView(
            state = state.state,
            onUiEvent = viewModel::onUiEvent,
        )
    }
}

@Composable
private fun CheckPermissionScreenView(
    state: CheckPermissionState,
    onUiEvent: (CheckPermissionUiEvent) -> Unit,
) {

    LaunchedEffect(Unit) {
        onUiEvent(CheckPermissionUiEvent.OnScreenDrawn)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .imePadding(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        when (state.permissionStatus) {
            PermissionState.Granted -> {
                NoteText(
                    text = state.permissionGrantedTitle,
                    color = NoteTheme.colors.textAccent,
                )
                NotePrimaryButton(
                    modifier = Modifier.padding(16.dp),
                    text = state.continueButtonTitle,
                    onClick = { onUiEvent(CheckPermissionUiEvent.OnContinueButtonClicked) },
                )
            }

            PermissionState.DeniedAlways -> {
                NoteText(
                    text = state.permissionTwiceDeniedTitle,
                    color = NoteTheme.colors.textAccent,
                )
                VSpacer(8.dp)
                NotePrimaryButton(
                    modifier = Modifier.padding(16.dp),
                    text = state.grantInSettingButtonTitle,
                    onClick = { onUiEvent(CheckPermissionUiEvent.OnGoToSettingsButtonClicked) },
                )
            }

            PermissionState.Denied,
            PermissionState.NotDetermined,
            PermissionState.NotGranted,
                -> {
                NotePrimaryButton(
                    modifier = Modifier.padding(16.dp),
                    text = state.requestPermissionButtonTitle,
                    onClick = { onUiEvent(CheckPermissionUiEvent.OnRequestPermissionButtonClicked) },
                )
            }
        }
    }
}