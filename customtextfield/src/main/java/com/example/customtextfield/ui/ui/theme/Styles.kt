package com.example.customtextfield.ui.ui.theme

import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import com.example.customtextfield.R
import com.example.customtextfield.ui.ui.theme.dimens.DimensSp.Sp10
import com.example.customtextfield.ui.ui.theme.dimens.DimensSp.Sp14
import com.example.customtextfield.ui.ui.theme.dimens.DimensSp.Sp16
import com.example.customtextfield.ui.ui.theme.dimens.DimensSp.Sp24
import org.jetbrains.annotations.ApiStatus.Internal

@Internal
object Styles {
    private val robotoFontFamily = FontFamily(
        Font(R.font.roboto_regular, FontWeight.Normal),
        Font(R.font.roboto_bold, FontWeight.Bold),
        Font(R.font.roboto_light, FontWeight.Light),
        Font(R.font.roboto_medium, FontWeight.Medium)
    )


    var roboto16Medium = TextStyle(
        fontSize = Sp16,
        lineHeight = Sp24,
        fontFamily = robotoFontFamily,
        fontWeight = FontWeight.Medium,
    )
    var roboto10Medium = TextStyle(
        fontSize = Sp10,
        lineHeight = Sp16,
        fontFamily = robotoFontFamily,
        fontWeight = FontWeight.Medium,
        platformStyle = PlatformTextStyle(includeFontPadding = false)
    )
    var roboto16Regular = TextStyle(
        fontSize = Sp16,
        fontWeight = FontWeight.Normal,
        fontFamily = robotoFontFamily,
        lineHeight = Sp24,
        platformStyle = PlatformTextStyle(includeFontPadding = false)
    )
    var roboto14Regular = TextStyle(
        fontSize = Sp14,
        fontWeight = FontWeight.Normal,
        fontFamily = robotoFontFamily,
        lineHeight = Sp16
    )

}