package com.template.cmp.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.sp
import com.template.cmp.R

val MaterialTypography: Typography = Typography(
    bodyMedium = EpsTypography.bodyText,
)

object EpsTypography {
    private val defaultStyle = TextStyle(
        fontFamily = NunitoFontFamily.nunitoRegular,
        fontSize = 14.sp,
        lineHeight = 16.59.sp,
    )
    val bodyText = defaultStyle.copy()

}

object NunitoFontFamily {
    val nunitoRegular = FontFamily(Font(R.font.nunito_regular))
    val nunitoMedium = FontFamily(Font(R.font.nunito_medium))
    val nunitoSemibold = FontFamily(Font(R.font.nunito_semibold))
    val nunitoBold = FontFamily(Font(R.font.nunito_bold))
}