package com.template.cmp.theme

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf

@OptIn(ExperimentalMaterial3Api::class)
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
//            LocalRippleConfiguration provides,
            content = content,
        )
    }
}

internal val LocalNoteColors = staticCompositionLocalOf<NoteThemeColors> {
    error("No EpsThemeColors provided")
}