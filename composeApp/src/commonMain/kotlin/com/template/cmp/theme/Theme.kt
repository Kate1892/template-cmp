package com.template.cmp.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf

@Composable
fun NoteTheme(
    content: @Composable () -> Unit,
) {
    val colorPallet = NotePallet

    MaterialTheme(
        typography = NoteTypography(),
        shapes = MaterialShapes,
    ) {
        CompositionLocalProvider(
            LocalNoteColors provides colorPallet,
            content = content,
        )
    }
}

internal val LocalNoteColors = staticCompositionLocalOf<NoteThemeColors> {
    error("No EpsThemeColors provided")
}