package com.template.cmp.theme

import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.sp
import org.jetbrains.compose.resources.Font
import template_cmp.composeapp.generated.resources.Res
import template_cmp.composeapp.generated.resources.nunito_bold
import template_cmp.composeapp.generated.resources.nunito_medium
import template_cmp.composeapp.generated.resources.nunito_regular
import template_cmp.composeapp.generated.resources.nunito_semibold

//val MaterialTypography: Typography = Typography(
//    bodyMedium = NoteTypography.bodyText,
//)


@Composable
fun NoteTypography(): Typography = Typography().run {
    val nunitoRegular = NunitoRegular()
    val nunitoMedium = NunitoMedium()
    val nunitoSemibold = NunitoSemibold()
    val nunitoBold = NunitoBold()

//    val defaultStyle = TextStyle(
//        fontFamily = nunitoRegular,
//        fontSize = 70.sp,
//        lineHeight = 16.59.sp,
//    )
//    val bodyText = defaultStyle.copy()

    return Typography(
        titleLarge = TextStyle(
            fontFamily = nunitoBold,
            fontSize = 70.sp,
            lineHeight = 16.59.sp,
        ),
        titleSmall = TextStyle(
            fontFamily = nunitoSemibold,
            fontSize = 30.sp,
            lineHeight = 16.59.sp,
        ),
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
