package com.example.customtextfield.widget.decorationBox.main.utils

import com.example.customtextfield.widget.decorationBox.core.TextFieldStatus
import com.example.customtextfield.widget.decorationBox.main.model.DsTypesIcons
import java.math.RoundingMode.DOWN
import java.text.DecimalFormat
import java.util.Locale


fun Double.formatMoney(): String {
    val format = DecimalFormat.getCurrencyInstance(Locale.forLanguageTag("es-MX"))
    format.minimumFractionDigits = 0
    format.roundingMode = DOWN
    return format.format(this).trim()
}

fun getIconByStatus(textFieldStatus: TextFieldStatus, dsTypesIcons: DsTypesIcons): Int? {
    return when(textFieldStatus){
        TextFieldStatus.ERROR -> dsTypesIcons.errorIcon
        TextFieldStatus.SUCCESS -> dsTypesIcons.successIcon
        TextFieldStatus.WARNING -> dsTypesIcons.warning
        else ->{
            dsTypesIcons.endIcon
        }
    }
}