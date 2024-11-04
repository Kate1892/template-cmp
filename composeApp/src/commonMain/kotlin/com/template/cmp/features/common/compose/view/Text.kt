package com.template.cmp.features.common.compose.view

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import com.template.cmp.theme.NoteTheme
import com.template.cmp.theme.NoteTypography

@Composable
fun NoteText(
    text: String,
    modifier: Modifier = Modifier,
    maxLines: Int = 1,
    color: Color = NoteTheme.colors.textPrimary,
    style: TextStyle = NoteTypography().bodyLarge,
    textAlign: TextAlign? = null,
    letterSpacing: Float = 0.0f,
) {
    Text(
        modifier = modifier,
        text = text,
        letterSpacing = TextUnit(letterSpacing, TextUnitType.Sp),
        maxLines = maxLines,
        color = color,
        style = style,
        textAlign = textAlign,
    )
}
