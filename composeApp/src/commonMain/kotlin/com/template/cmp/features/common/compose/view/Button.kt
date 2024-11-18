package com.template.cmp.features.common.compose.view

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.unit.dp
import com.template.cmp.theme.NoteShapes
import com.template.cmp.theme.NoteTheme
import com.template.cmp.theme.NoteTypography

@Composable
fun NotePrimaryButton(
    text: String,
    modifier: Modifier = Modifier,
    isEnabled: Boolean = true,
    onClick: () -> Unit = {},
) {
    val focusManager = LocalFocusManager.current

    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .fillMaxWidth()
            .height(48.dp)
            .clip(NoteShapes.primary)
            .background(color = NoteTheme.colors.buttonAccent)
            .clickable(
                enabled = isEnabled,
                onClick = {
                    focusManager.clearFocus()
                    onClick()
                }
            )
    ) {
        NoteText(
            text = text,
            style = NoteTypography().bodyLarge,
            color = NoteTheme.colors.buttonAccentText,
        )
    }
}