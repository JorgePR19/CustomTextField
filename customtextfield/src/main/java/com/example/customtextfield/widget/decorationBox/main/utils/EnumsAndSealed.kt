package com.example.customtextfield.widget.decorationBox.main.utils

enum class TextFieldStatus {
    ENABLE, DISABLE, ERROR, EDITING, SUCCESS, WARNING
}

sealed class DsInputType {
    data object Default : DsInputType()
    data object Simple : DsInputType()
    data object Money : DsInputType()
    data object Password : DsInputType()
}

enum class DsKayBoardActions {
    ON_DONE,
    ON_NEXT
}