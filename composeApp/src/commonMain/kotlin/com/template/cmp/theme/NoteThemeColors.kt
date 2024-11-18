package com.template.cmp.theme

import androidx.compose.runtime.Stable
import androidx.compose.ui.graphics.Color

@Stable
data class NoteThemeColors(
    val textPrimary: Color,
    val textAccent: Color,
    val buttonPrimary: Color,
    val buttonPrimaryText: Color,
    val buttonAccent: Color,
    val buttonAccentText: Color,
    val inputField: Color,
    val inputFieldHintText: Color,
    val screenBackground: Color,

    val purpleGradientStartColor: Color,
    val purpleGradientMiddleColor: Color,
    val purpleGradientEndColor: Color,
)

val NotePallet: NoteThemeColors = NoteThemeColors(
    textPrimary = NoteColors.black,
    textAccent = NoteColors.white,
    buttonPrimary = NoteColors.primary,
    buttonPrimaryText = NoteColors.white,
    buttonAccent = NoteColors.white,
    buttonAccentText = NoteColors.primary,
    inputField = NoteColors.fieldBackground,
    inputFieldHintText = NoteColors.grey,
    screenBackground = NoteColors.primary,

    purpleGradientStartColor = NoteColors.purpleGradientStart,
    purpleGradientMiddleColor = NoteColors.purpleGradientMiddle,
    purpleGradientEndColor = NoteColors.purpleGradientEnd,
)