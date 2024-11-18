package com.template.cmp.common.view

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.lifecycle.compose.LocalLifecycleOwner
import com.template.cmp.common.mvvm.DefaultUiEvent
import com.template.cmp.common.mvvm.LceState

@Composable
fun BaseScreen(
    lceState: LceState,
//    defaultEffectFlow: SharedFlow<DefaultViewModelEffect>,
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


}

@Composable
private fun LceStateHandlerView(
    lceState: LceState,
) {
//    when (val errorState = lceState.errorState) {
//        // TODO
//        null -> Unit
//        is ErrorState.FullScreenError.View -> Unit
//        is ErrorState.FullScreenError.Screen -> {
//            FullScreenErrorScreen(errorState = errorState)
//        }
//    }

    if (lceState.isLoading) {
//        LoadingDialog()
    }
}
