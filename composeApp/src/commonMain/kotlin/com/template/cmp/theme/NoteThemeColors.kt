package com.template.cmp.theme

import androidx.compose.runtime.Stable
import androidx.compose.ui.graphics.Color

@Stable
data class NoteThemeColors(
    val textPrimary: Color,
    val buttonPrimary: Color,
    val buttonPrimaryText: Color,
    val inputField: Color,
    val inputFieldHintText: Color,
    val screenBackground: Color,

    val purpleGradientStartColor: Color,
    val purpleGradientMiddleColor: Color,
    val purpleGradientEndColor: Color,
)

val NotePallet: NoteThemeColors = NoteThemeColors(
    textPrimary = NoteColors.black,
    buttonPrimary = NoteColors.white,
    buttonPrimaryText = NoteColors.grey,
    inputField = NoteColors.fieldBackground,
    inputFieldHintText = NoteColors.grey,
    screenBackground = NoteColors.white,

    purpleGradientStartColor = NoteColors.purpleGradientStart,
    purpleGradientMiddleColor = NoteColors.purpleGradientMiddle,
    purpleGradientEndColor = NoteColors.purpleGradientEnd,
)