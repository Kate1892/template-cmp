package com.themplate.cmp

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import io.github.alexzhirkevich.cupertino.adaptive.AdaptiveWidget
import io.github.alexzhirkevich.cupertino.adaptive.ExperimentalAdaptiveApi

@OptIn(ExperimentalAdaptiveApi::class)
@Composable
fun CustomComponent(
    modifier: Modifier = Modifier,
) {
    AdaptiveWidget(
        material = { Text("Your Custom design here") },
        cupertino = { Text("Your Custom design here") }
    )
}

@Composable
expect fun CustomButton(label: String, onClick: () -> Unit)