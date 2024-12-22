package com.example.customtextfield.widget.decorationBox.main.model

import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import com.example.customtextfield.widget.decorationBox.core.DsInputType
import com.example.customtextfield.widget.decorationBox.core.TextFieldStatus


data class DsInputModel(
    val inputType: DsInputType = DsInputType.Simple,
    val textFieldStatus: TextFieldStatus = TextFieldStatus.ENABLE,
    val textValue: TextFieldValue,
    val placeHolder: String = "",
    val dsTypesIcons: DsTypesIcons = DsTypesIcons(),
    val helperText: String = "",
    val label: String = "",
    val properties: DsInputProperties = DsInputProperties()
)

data class DsInputProperties(
    val maxLength: Int = 0,
    val containsRegex: String = "",
    val keyBoardPermit: String = "",
    val minValue: Int = 0,
    val maxValue: Int = 0,
    val keyboardType: KeyboardType = KeyboardType.Text,
    val imeAction: ImeAction = ImeAction.Done,
    val countLength:Boolean = false
)

data class DsTypesIcons(
    val endIcon: Int? = null,
    val successIcon: Int? = null,
    val errorIcon: Int? = null,
    val warning: Int? = null,
)