package com.example.customtextfield.widget.decorationBox.core.textfield

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import com.example.customtextfield.ui.ui.theme.Styles.roboto16Regular
import com.example.customtextfield.ui.ui.theme.dimens.DimensDp.Dp4
import com.example.customtextfield.ui.ui.theme.dimens.DimensDp.Dp8
import com.example.customtextfield.ui.ui.theme.dimens.DimensSp.Sp10
import com.example.customtextfield.ui.ui.theme.gray800Color
import com.example.customtextfield.widget.decorationBox.main.utils.DsKayBoardActions
import com.example.customtextfield.widget.decorationBox.main.utils.TextFieldStatus
import com.example.customtextfield.widget.decorationBox.core.decoration.DsDecorationBox
import org.jetbrains.annotations.ApiStatus.Internal

@Internal
@Composable
fun DSBaseTextField(
    value: TextFieldValue,
    onValueChange: (TextFieldValue) -> Unit,
    onFocusChange: (Boolean) -> Unit,
    placeholder: String = "",
    @DrawableRes startIcon: Int? = null,
    @DrawableRes endIcon: Int? = null,
    endIconAction: (() -> Unit)? = null,
    textFieldStatus: TextFieldStatus,
    keyboardOptions: KeyboardOptions,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    label: String = "",
    keyBoardAction: ((DsKayBoardActions) -> Unit)? = null,
    modifier: Modifier,
    helperText: String = "",
    countLength: Boolean,
    maxLength: Int = 0
) {
    var focusState by rememberSaveable {
        mutableStateOf(false)
    }

    var currentLength by rememberSaveable {
        mutableStateOf(0)
    }

    Column(modifier = Modifier.fillMaxWidth()) {
        BasicTextField(
            value = value,
            modifier = modifier
                .fillMaxWidth()
                .onFocusChanged {
                    focusState = it.isFocused
                    onFocusChange(it.isFocused)
                },
            enabled = textFieldStatus != TextFieldStatus.DISABLE,
            onValueChange = {
                currentLength = it.text.length
                onValueChange(TextFieldValue(text = it.text, selection = it.selection))
            },
            decorationBox = { innerTextField ->
                DsDecorationBox(
                    innerTextField,
                    value = value.text,
                    placeholder = placeholder,
                    startIcon = startIcon,
                    endIcon = endIcon,
                    endIconAction = endIconAction,
                    focusState = focusState,
                    textFieldStatus = textFieldStatus,
                    label = label,
                )
            },
            textStyle = roboto16Regular.copy(color = gray800Color),
            maxLines = 1,
            keyboardOptions = keyboardOptions,
            keyboardActions = KeyboardActions(
                onDone = {
                    if (keyBoardAction != null) keyBoardAction(DsKayBoardActions.ON_DONE)
                },
                onNext = {
                    if (keyBoardAction != null) keyBoardAction(DsKayBoardActions.ON_NEXT)
                }
            ),
            visualTransformation = visualTransformation,
        )
        Row(modifier = Modifier.fillMaxWidth()) {
            if (helperText.isNotEmpty()) {
                Text(
                    helperText,
                    modifier = Modifier.padding(top = Dp4, start = Dp8),
                    style = getStyle(textFieldStatus)
                )
            }
            Spacer(modifier = Modifier.weight(1f))
            if (countLength) {
                Text(
                    "$currentLength / $maxLength",
                    modifier = Modifier.padding(top = Dp4, start = Dp8),
                    style = getStyle(textFieldStatus).copy(fontSize = Sp10)
                )
            }
        }
    }
}