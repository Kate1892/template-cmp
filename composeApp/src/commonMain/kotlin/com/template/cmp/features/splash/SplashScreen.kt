package com.template.cmp.features.splash

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.scaleIn
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.imePadding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.text.style.TextAlign
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

private const val ANIMATION_DURATION = 500

@Composable
fun SplashScreen(
    viewModel: SplashViewModel = koinViewModel()
) {
    val state by viewModel.stateFlow.collectAsState()

    BaseScreen(
        lceState = state.lceState,
        onDefaultUiEvent = viewModel::onDefaultUiEvent,
    ) {
        SplashScreenView(state = state.state)
    }

}


@Composable
private fun SplashScreenView(
    state: SplashState,
) {
    var isVisible by remember { mutableStateOf(false) }

    val gradient = Brush.linearGradient(
        colors = listOf(
            NoteTheme.colors.purpleGradientStartColor,
            NoteTheme.colors.purpleGradientMiddleColor,
            NoteTheme.colors.purpleGradientStartColor
        ),
    )
    Box(
        modifier = Modifier
            .fillMaxSize()
            .imePadding()
            .background(gradient),
        contentAlignment = Alignment.Center,
    ) {
        AnimatedVisibility(
            visible = isVisible,
            enter = fadeIn(animationSpec = tween(ANIMATION_DURATION)) +
                    scaleIn(animationSpec = tween(ANIMATION_DURATION)),
        ) {
            Text(
                text = state.screenTitle,
                style = NoteTypography().titleLarge,
                textAlign = TextAlign.Center,
                color = NoteTheme.colors.textAccent,
            )
        }
    }
    LaunchedEffect(Unit) {
        isVisible = true
    }
}
