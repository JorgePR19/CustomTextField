package com.example.customtextfield.widget.decorationBox.core.textfield

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import com.example.customtextfield.ui.ui.theme.Styles.roboto14Regular
import com.example.customtextfield.ui.ui.theme.gray800Color
import com.example.customtextfield.ui.ui.theme.green500Color
import com.example.customtextfield.ui.ui.theme.red800Color
import com.example.customtextfield.ui.ui.theme.yellow500Color
import com.example.customtextfield.widget.decorationBox.main.utils.TextFieldStatus
import org.jetbrains.annotations.ApiStatus.Internal

private fun getHelperTextStyle(texColor: Color) = roboto14Regular.copy(color = texColor)

@Internal
@Composable
fun getStyle(textFieldStatus: TextFieldStatus): TextStyle {
    return when (textFieldStatus) {
        TextFieldStatus.SUCCESS -> getHelperTextStyle(green500Color)
        TextFieldStatus.ERROR -> getHelperTextStyle(red800Color)
        TextFieldStatus.WARNING -> getHelperTextStyle(yellow500Color)
        else -> getHelperTextStyle(gray800Color)
    }
}