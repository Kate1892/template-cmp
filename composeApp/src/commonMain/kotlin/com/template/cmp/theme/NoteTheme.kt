package com.template.cmp.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable


object NoteTheme {
    val colors: NoteThemeColors
        @Composable
        @ReadOnlyComposable
        get() = LocalNoteColors.current
}
