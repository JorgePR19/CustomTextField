package com.example.customtextfield.widget.decorationBox.main

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import com.example.customtextfield.R
import com.example.customtextfield.widget.decorationBox.core.DsKayBoardActions
import com.example.customtextfield.widget.decorationBox.core.textfield.DSBaseTextField
import com.example.customtextfield.widget.decorationBox.main.model.DsInputModel
import com.example.customtextfield.widget.decorationBox.main.utils.PasswordTransformation


/**
 *
 * input format Password
 *
 * @param dsInputModel this param contain all need for create an Input
 * @param onValueChange lambda emit a TextFieldValue
 * @param onFocusChange labda emit if the component is focused
 * @param keyBoardAction emit the keyBoardAction example -- onDONE, OnNext etc..
 * @param Modifier
 *
 * @param dsInputModel.properties.keyBoardPermit keyEvents allowed
 *Example -- package mx.com.miapp.utils.dscomponents.dsutil.input
 *
 *         const val validateNumbers: String = "1234567890"
 *         const val validLowercase: String = "abcdefghijklmnñopqrstuvwxyz"
 *         const val validateUppercase: String = "ABCDEFGHIJKLMNÑOPQRSTUVWXYZ"
 */

@Composable
fun DsPasswordTextField(
    dsInputModel: DsInputModel,
    onValueChange: (TextFieldValue) -> Unit,
    onFocusChange: (Boolean) -> Unit,
    keyBoardAction: (DsKayBoardActions) -> Unit,
    modifier: Modifier
) {
    var showPass by rememberSaveable {
        mutableStateOf(false)
    }

    DSBaseTextField(
        value = dsInputModel.textValue,
        onValueChange = { newText ->
            if (newText.text.all { it in dsInputModel.properties.keyBoardPermit }) onValueChange(
                newText
            )
        },
        onFocusChange = { state ->
            onFocusChange(state)
        },
        placeholder = dsInputModel.placeHolder,
        icon = if (!showPass) R.drawable.eye_visible_icon else R.drawable.eye_invisible_icon,
        endIconAction = {
            showPass = !showPass
        },
        textFieldStatus = dsInputModel.textFieldStatus,
        keyboardOptions = KeyboardOptions(
            autoCorrect = false,
            keyboardType = dsInputModel.properties.keyboardType,
            imeAction = dsInputModel.properties.imeAction
        ),
        visualTransformation = if (showPass) VisualTransformation.None else PasswordTransformation(),
        label = dsInputModel.label,
        keyBoardAction = {
            keyBoardAction(it)
        },
        modifier = modifier,
        helperText = dsInputModel.helperText,
        maxLength = dsInputModel.properties.maxLength,
        countLength = dsInputModel.properties.countLength
    )
}