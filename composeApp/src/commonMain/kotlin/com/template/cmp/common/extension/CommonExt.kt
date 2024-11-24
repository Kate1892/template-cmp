package com.template.cmp.common.extension

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.repeatOnLifecycle
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

const val COMPOSE_PREVIEW_BACKGROUND_COLOR = 0xFFFFFFFF

@Composable
fun OnScreenResumed(
    lifecycleOwner: LifecycleOwner,
    action: () -> Unit,
) {
    val lifecycleState by lifecycleOwner.lifecycle.currentStateFlow.collectAsState()
    LaunchedEffect(lifecycleState) {
        when (lifecycleState) {
            Lifecycle.State.RESUMED -> {
                action()
            }

            else -> {}
        }
    }
}

fun <T> Flow<T>.launchWhenStarted(lifecycle: Lifecycle, lifecycleScope: LifecycleCoroutineScope) {
    lifecycleScope.launch {
        lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
            this@launchWhenStarted.collect()
        }
    }
}
