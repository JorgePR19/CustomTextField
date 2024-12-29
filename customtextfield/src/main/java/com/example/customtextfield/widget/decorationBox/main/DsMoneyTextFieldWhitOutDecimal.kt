package com.example.customtextfield.widget.decorationBox.main

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.TextFieldValue
import com.example.customtextfield.widget.decorationBox.main.utils.DsKayBoardActions
import com.example.customtextfield.widget.decorationBox.core.textfield.DSBaseTextField
import com.example.customtextfield.widget.decorationBox.main.model.DsInputModel
import com.example.customtextfield.widget.decorationBox.main.utils.formatMoney
import com.example.customtextfield.widget.decorationBox.main.utils.getIconByStatus


/**
 *
 * input format money example $1,000 no contain decimal
 *
 * @param dsInputModel this param contain all need for create an Input
 * @param onValueChange lambda emit a TextFieldValue
 * @param onFocusChange labda emit if the component is focused
 * @param keyBoardAction emit the keyBoardAction example -- onDONE, OnNext etc..
 * @param Modifier
 *
 * @param dsInputModel.properties.maxLength specify when create the model max length 9
 * formattedText.length in 0..dsInputModel.properties.maxLength
 *
 */

@Composable
fun DsMoneyTextFieldWhitOutDecimal(
    dsInputModel: DsInputModel,
    onValueChange: (TextFieldValue) -> Unit,
    onFocusChange: (Boolean) -> Unit,
    keyBoardAction: (DsKayBoardActions) -> Unit,
    modifier: Modifier
) {
    DSBaseTextField(
        value = dsInputModel.textValue,
        onValueChange = { newText ->
            var formattedText = newText.text.replace("$", "").replace(",", "")
            formattedText = formattedText.ifEmpty { "0" }
            val newValueMoney = formattedText.toDouble().formatMoney()
            if (newText.text.all { it in dsInputModel.properties.keyBoardPermit }
                && formattedText.length in 0..dsInputModel.properties.maxLength) {
                onValueChange(
                    TextFieldValue(
                        text = newValueMoney,
                        selection = TextRange(newValueMoney.length)
                    )
                )
            }
        },
        onFocusChange = { state ->
            onFocusChange(state)
        },
        textFieldStatus = dsInputModel.textFieldStatus,
        keyboardOptions = KeyboardOptions(
            autoCorrect = false,
            keyboardType = dsInputModel.properties.keyboardType,
            imeAction = dsInputModel.properties.imeAction
        ),
        label = dsInputModel.label,
        keyBoardAction = {
            keyBoardAction(it)
        },
        modifier = modifier,
        helperText = dsInputModel.helperText,
        startIcon = dsInputModel.dsTypesIcons.startIcon,
        endIcon = getIconByStatus(dsInputModel.textFieldStatus, dsInputModel.dsTypesIcons),
        maxLength = dsInputModel.properties.maxLength,
        countLength = dsInputModel.properties.countLength
    )
}