package com.template.cmp.theme

import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.sp
import org.jetbrains.compose.resources.Font
import template_cmp.composeapp.generated.resources.Res
import template_cmp.composeapp.generated.resources.nunito_bold
import template_cmp.composeapp.generated.resources.nunito_extra_bold
import template_cmp.composeapp.generated.resources.nunito_medium
import template_cmp.composeapp.generated.resources.nunito_regular
import template_cmp.composeapp.generated.resources.nunito_semibold


@Composable
fun NoteTypography(): Typography = Typography().run {

    val nunitoRegular = NunitoRegular()
    val nunitoMedium = NunitoMedium()
    val nunitoSemibold = NunitoSemibold()
    val nunitoBold = NunitoBold()
    val nunitoExtraBold = NunitoExtraBold()

    val defaultStyle = TextStyle(
        fontFamily = nunitoRegular,
        fontSize = 16.sp,
        lineHeight = 14.59.sp,
    )

    val titleLarge = defaultStyle.copy(
        fontFamily = nunitoExtraBold,
        fontSize = 26.sp,
        lineHeight = 30.44.sp,
    )

    val titleMedium = defaultStyle.copy(
        fontFamily = nunitoBold,
        fontSize = 20.sp,
        lineHeight = 22.sp,
    )

    val title = defaultStyle.copy(
        fontFamily = nunitoMedium,
        fontSize = 18.sp,
        lineHeight = 22.sp,
    )

    val bodyTextLarge = defaultStyle.copy(
        fontFamily = nunitoSemibold,
        fontSize = 18.sp,
        lineHeight = 22.sp,
    )
    val bodyTextMedium = defaultStyle.copy(
        fontFamily = nunitoMedium,
        fontSize = 16.sp,
        lineHeight = 22.sp,
    )

    val label = defaultStyle.copy(
        fontFamily = nunitoRegular,
        fontSize = 14.sp,
        lineHeight = 14.sp,
    )

    return Typography(
        titleLarge = titleLarge,
        titleMedium = titleMedium,
        titleSmall = title,
        bodyLarge = bodyTextLarge,
        bodyMedium = bodyTextMedium,
        bodySmall = defaultStyle,
        labelSmall = label,
    )
}

@Composable
fun NunitoRegular() = FontFamily(Font(Res.font.nunito_regular))

@Composable
fun NunitoMedium() = FontFamily(Font(Res.font.nunito_medium))

@Composable
fun NunitoSemibold() = FontFamily(Font(Res.font.nunito_semibold))

@Composable
fun NunitoBold() = FontFamily(Font(Res.font.nunito_bold))

@Composable
fun NunitoExtraBold() = FontFamily(Font(Res.font.nunito_extra_bold))
