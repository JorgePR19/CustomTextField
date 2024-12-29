package com.example.customtextfield.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.customtextfield.R
import com.example.customtextfield.ui.ui.theme.LibraryCustomTextFieldTheme
import com.example.customtextfield.widget.decorationBox.main.utils.TextFieldStatus
import com.example.customtextfield.widget.decorationBox.main.DsMoneyTextFieldWhitOutDecimal
import com.example.customtextfield.widget.decorationBox.main.DsPasswordTextField
import com.example.customtextfield.widget.decorationBox.main.DsSimpleTextField
import com.example.customtextfield.widget.decorationBox.main.model.DsInputModel
import com.example.customtextfield.widget.decorationBox.main.model.DsInputProperties
import com.example.customtextfield.widget.decorationBox.main.model.DsTypesIcons

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LibraryCustomTextFieldTheme {
                GreetingPreview()
            }
        }
    }
}



@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    val focusManager: FocusManager = LocalFocusManager.current

    var passwordModel by remember {
        mutableStateOf(
            DsInputModel(
                textValue = TextFieldValue(""),
                label = "Password",
                properties = DsInputProperties(
                    maxLength = 15,
                    countLength = true,
                    keyBoardPermit = "123456789abcdefghijklmnñopqrstuvwxyzABCDEFGHIJKLMNÑOPQRSTUVWXYZ",
                )
            )
        )
    }

    var moneyModel by remember {
        mutableStateOf(
            DsInputModel(
                textValue = TextFieldValue(""),
                helperText = "Ingresa una cantidad entre $100 a $5,000",
                label = "Monto",
                properties = DsInputProperties(
                    keyBoardPermit = "1234567890$,.",
                    keyboardType = KeyboardType.Number,
                    minValue = 100,
                    maxValue = 5000,
                    maxLength = 9
                )
            )
        )
    }

    var simpleModel by remember {
        mutableStateOf(
            DsInputModel(
                textValue = TextFieldValue(""),
                helperText = "SimpleTextField",
                label = "TextField status",
                dsTypesIcons = DsTypesIcons(
                    errorIcon = R.drawable.ic_error,
                    successIcon = R.drawable.ic_success,
                    warning = R.drawable.ic_warning
                )
            )
        )
    }

    var simpleModelDisable by remember {
        mutableStateOf(
            DsInputModel(
                textValue = TextFieldValue(""),
                helperText = "SimpleTextField Disable",
                label = "TextField status Disable",
                textFieldStatus = TextFieldStatus.DISABLE
            )
        )
    }


    Column(
        Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        DsPasswordTextField(
            dsInputModel = passwordModel,
            onValueChange = {
                passwordModel = passwordModel.copy(textValue = it)
                if (passwordModel.properties.maxLength > 0 && it.text.length > passwordModel.properties.maxLength) {
                    passwordModel = passwordModel.copy(textFieldStatus = TextFieldStatus.ERROR)
                } else {
                    passwordModel = passwordModel.copy(textFieldStatus = TextFieldStatus.ENABLE)
                }
            },
            onFocusChange = {},
            keyBoardAction = {
                focusManager.clearFocus()
            },
            modifier = Modifier
        )

        Spacer(modifier = Modifier.height(16.dp))

        DsMoneyTextFieldWhitOutDecimal(
            dsInputModel = moneyModel,
            onValueChange = {
                moneyModel = moneyModel.copy(textValue = it)
            },
            onFocusChange = {},
            keyBoardAction = {
                focusManager.clearFocus()

                when {
                    moneyModel.textValue.text.isNotEmpty() -> {
                        val value =
                            moneyModel.textValue.text.replace("$", "").replace(",", "").toInt()

                        when {
                            value < moneyModel.properties.minValue -> {
                                moneyModel = moneyModel.copy(
                                    textFieldStatus = TextFieldStatus.ERROR,
                                    helperText = "El numero ingresado es menor al minimo permitido"
                                )
                            }

                            value > moneyModel.properties.maxValue -> {
                                moneyModel = moneyModel.copy(
                                    textFieldStatus = TextFieldStatus.ERROR,
                                    helperText = "El numero ingresado es mayor al maximo permitido"
                                )

                            }

                            else -> {
                                moneyModel = moneyModel.copy(
                                    textFieldStatus = TextFieldStatus.ENABLE,
                                    helperText = "Ingresa una cantidad entre $100 a $5,000"
                                )
                            }
                        }
                    }

                    else -> Unit
                }
            },
            modifier = Modifier
        )

        Spacer(modifier = Modifier.height(16.dp))

        DsSimpleTextField(
            dsInputModel = simpleModel,
            onFocusChange = {

            },
            onValueChange = {
                simpleModel = simpleModel.copy(textValue = it)
            },
            keyBoardAction = {
                focusManager.clearFocus()

                when {
                    simpleModel.textValue.text.isNotEmpty() -> {
                        val value =
                            simpleModel.textValue.text

                        when {
                            value == "Error" -> {
                                simpleModel = simpleModel.copy(
                                    textFieldStatus = TextFieldStatus.ERROR,
                                    helperText = "TextFieldStatus.ERROR"
                                )
                            }

                            value == "Success" -> {
                                simpleModel = simpleModel.copy(
                                    textFieldStatus = TextFieldStatus.SUCCESS,
                                    helperText = "TextFieldStatus.SUCCESS"
                                )

                            }

                            value == "Warning" -> {
                                simpleModel = simpleModel.copy(
                                    textFieldStatus = TextFieldStatus.WARNING,
                                    helperText = "TextFieldStatus.WARNING"
                                )

                            }

                            else -> {
                                simpleModel = simpleModel.copy(
                                    textFieldStatus = TextFieldStatus.ENABLE,
                                    helperText = "SimpleTextField"
                                )
                            }
                        }
                    }

                    else -> Unit
                }
            },
            modifier = Modifier
        )

        Spacer(modifier = Modifier.height(16.dp))

        DsSimpleTextField(dsInputModel = simpleModelDisable,
            onFocusChange = {}, onValueChange = {}, keyBoardAction = {}, modifier = Modifier)
    }
}