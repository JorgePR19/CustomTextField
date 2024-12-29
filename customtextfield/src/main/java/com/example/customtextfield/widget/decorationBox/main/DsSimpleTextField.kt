package com.example.customtextfield.widget.decorationBox.main

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import com.example.customtextfield.R.drawable.*
import com.example.customtextfield.widget.decorationBox.core.textfield.DSBaseTextField
import com.example.customtextfield.widget.decorationBox.main.model.DsInputModel
import com.example.customtextfield.widget.decorationBox.main.utils.DsKayBoardActions
import com.example.customtextfield.widget.decorationBox.main.utils.getIconByStatus


/**
 *
 * input format Simple
 *
 * @param dsInputModel this param contain all need for create an Input
 * @param onValueChange lambda emit a TextFieldValue
 * @param onFocusChange labda emit if the component is focused
 * @param keyBoardAction emit the keyBoardAction example -- onDONE, OnNext etc..
 * @param Modifier
 */

@Composable
fun DsSimpleTextField(
    dsInputModel: DsInputModel,
    onValueChange: (TextFieldValue) -> Unit,
    endIconAction: (() -> Unit)? = null,
    onFocusChange: (Boolean) -> Unit,
    keyBoardAction: (DsKayBoardActions) -> Unit,
    modifier: Modifier
) {
    DSBaseTextField(
        value = dsInputModel.textValue,
        onValueChange = {
            onValueChange(it)
        },
        onFocusChange = { state ->
            onFocusChange(state)
        },
        placeholder = dsInputModel.placeHolder,
        startIcon = dsInputModel.dsTypesIcons.startIcon,
        endIcon = getIconByStatus(dsInputModel.textFieldStatus, dsInputModel.dsTypesIcons),
        endIconAction = endIconAction,
        textFieldStatus = dsInputModel.textFieldStatus,
        keyboardOptions = KeyboardOptions(
            autoCorrect = false,
            keyboardType = KeyboardType.Text,
            imeAction = dsInputModel.properties.imeAction
        ),
        keyBoardAction = {
            keyBoardAction(it)
        },
        label = dsInputModel.label,
        modifier = modifier,
        helperText = dsInputModel.helperText,
        maxLength = dsInputModel.properties.maxLength,
        countLength = dsInputModel.properties.countLength
    )
}
