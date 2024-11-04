package com.template.cmp.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable


object NoteTheme {
    val colors: NoteThemeColors
        @Composable
        @ReadOnlyComposable
        get() = LocalNoteColors.current
}

//@OptIn(ExperimentalMaterial3Api::class)
//val EpsRippleConfiguration = RippleConfiguration(
//    color = EpsColors.rippleColor,
//    rippleAlpha = RippleAlpha(
//        draggedAlpha = 0.3f,
//        focusedAlpha = 0.3f,
//        hoveredAlpha = 0.3f,
//        pressedAlpha = 0.3f,
//    )
//)