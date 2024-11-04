package com.template.cmp.theme

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Shapes
import androidx.compose.ui.unit.dp

val MaterialShapes: Shapes = Shapes(
    medium = NoteShapes.primary,
    extraLarge = NoteShapes.round,
)

object NoteShapes {
    val small = RoundedCornerShape(4.dp)
    val primary = RoundedCornerShape(8.dp)
    val medium = RoundedCornerShape(12.dp)
    val round = RoundedCornerShape(100.dp)
}