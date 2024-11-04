package com.template.cmp.common.mvvm

import androidx.compose.runtime.Immutable

data class ScreenState<T>(
    val state: T,
    val lceState: LceState = LceState(),
)

@Immutable
data class LceState(
    val isLoading: Boolean = false,
)
