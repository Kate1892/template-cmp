package com.template.cmp.common.view

import androidx.compose.runtime.Composable

@Composable
fun BaseScreen(
    content: @Composable () -> Unit,
) {
    content()
}