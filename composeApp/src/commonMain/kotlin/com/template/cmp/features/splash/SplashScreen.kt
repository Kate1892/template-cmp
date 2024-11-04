package com.template.cmp.features.splash

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.template.cmp.common.view.BaseScreen
import com.template.cmp.features.splash.mvvm.SplashState
import com.template.cmp.features.splash.mvvm.SplashViewModel
import com.template.cmp.navigation.NavigationAction
import com.template.cmp.theme.NoteTheme
import com.template.cmp.theme.NoteTypography
import org.koin.compose.viewmodel.koinViewModel

fun NavGraphBuilder.navRouteSplash() {
    composable<NavigationAction.NavigateToSplash> {
        SplashScreen()
    }
}

@Composable
fun SplashScreen(
    viewModel: SplashViewModel = koinViewModel()
) {
    val state by viewModel.stateFlow.collectAsState()

    BaseScreen(
        lceState = state.lceState,
//        defaultEffectFlow = viewModel.defaultEffectFlow,
//        onDefaultUiEvent = viewModel::onDefaultUiEvent,
    ) {
        SplashScreenView(state = state.state)
    }

}


@Composable
private fun SplashScreenView(
    state: SplashState,
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .imePadding()
            .background(NoteTheme.colors.screenBackground),
        contentAlignment = Alignment.Center,
    ) {
        Column {
            Text(
                text = "HI!",
                style = NoteTypography().titleLarge,
                textAlign = TextAlign.Center,
                color = Color.Red,
            )
            Spacer(modifier = Modifier.height(20.dp))
            Text(
                text = "from out heart",
                style = NoteTypography().titleSmall,
                textAlign = TextAlign.Center,
                color = Color.Red,
            )
        }
    }
}
