package com.template.cmp.common.view

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.lifecycle.compose.LocalLifecycleOwner
import com.template.cmp.common.extension.OnScreenResumed
import com.template.cmp.common.mvvm.DefaultUiEvent
import com.template.cmp.common.mvvm.LceState

@Composable
fun BaseScreen(
    lceState: LceState,
    onDefaultUiEvent: (DefaultUiEvent) -> Unit,
    content: @Composable () -> Unit,
) {
    DisposableEffect(Unit) {
        onDefaultUiEvent(DefaultUiEvent.OnScreenCreated)

        onDispose {
            onDefaultUiEvent(DefaultUiEvent.OnScreenDestroyed)
        }
    }

    content()

    LceStateHandlerView(lceState = lceState)

    OnScreenResumed(lifecycleOwner = LocalLifecycleOwner.current) {
        onDefaultUiEvent(DefaultUiEvent.OnScreenResumed)
    }
}

@Composable
private fun LceStateHandlerView(
    lceState: LceState,
) {
    if (lceState.isLoading) {
//        LoadingDialog()
    }
}
