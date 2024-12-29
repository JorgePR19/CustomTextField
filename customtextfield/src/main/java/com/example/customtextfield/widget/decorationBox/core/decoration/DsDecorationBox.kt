package com.example.customtextfield.widget.decorationBox.core.decoration

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.customtextfield.R
import com.example.customtextfield.ui.ui.theme.dimens.DimensDp.Dp2
import com.example.customtextfield.ui.ui.theme.dimens.DimensDp.Dp3
import com.example.customtextfield.ui.ui.theme.dimens.DimensDp.Dp46
import com.example.customtextfield.ui.ui.theme.dimens.DimensDp.Dp50
import com.example.customtextfield.ui.ui.theme.dimens.DimensDp.Dp60
import com.example.customtextfield.ui.ui.theme.dimens.DimensDp.Dp8
import com.example.customtextfield.widget.decorationBox.main.utils.TextFieldStatus
import org.jetbrains.annotations.ApiStatus.Internal

@Internal
@Composable
fun DsDecorationBox(
    innerTextField: @Composable () -> Unit,
    value: String,
    placeholder: String = "",
    @DrawableRes startIcon: Int? = null,
    @DrawableRes endIcon: Int? = null,
    endIconAction: (() -> Unit)? = null,
    focusState: Boolean,
    textFieldStatus: TextFieldStatus,
    label: String
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxWidth()
    ) {
        ConstraintLayout(
            modifier = Modifier
                .wrapContentHeight()
        ) {
            val (circleContainer, textContainer) = createRefs()

            Box(modifier = Modifier
                .shadow(elevation = Dp3, shape = CircleShape, clip = true)
                .clip(CircleShape)
                .border(
                    width = getUnderLineHeightBox(focusState, placeholder, value),
                    color = getUnderLineBox(focusState, textFieldStatus),
                    shape = CircleShape
                )
                .background(Color.White)
                .fillMaxWidth()
                .height(Dp46)
                .constrainAs(textContainer) {
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                    start.linkTo(parent.start)
                }
            ) {
                Row(
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(end = Dp8)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxHeight()
                            .weight(1f)
                            .padding(start = Dp60, top = Dp2, bottom = Dp2),
                        verticalArrangement = Arrangement.Center
                    ) {
                        if (label.isNotEmpty()) Text(
                            label,
                            style = setLabelStyle(focusState, placeholder, value, textFieldStatus)
                        )
                        if (focusState || value.isNotEmpty() || placeholder.isNotEmpty()) {
                            if (value.isEmpty() && placeholder.isNotEmpty()) {
                                Text(
                                    text = placeholder,
                                    style = getPlaceHolderStyle(textFieldStatus)
                                )
                            } else {
                                Spacer(modifier = Modifier.weight(1f))
                                Box(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(end = Dp8)
                                ) {
                                    innerTextField()
                                }
                                Spacer(modifier = Modifier.weight(1f))
                            }
                        }
                    }

                    if (endIcon != null) {
                        IconButton(onClick = {
                            if (endIconAction != null) endIconAction()
                        }, modifier = Modifier.size(24.dp)) {
                            Image(
                                painter = painterResource(endIcon),
                                contentDescription = "",
                            )
                        }
                    }
                }
            }
            Box(
                modifier = Modifier
                    .shadow(elevation = Dp3, shape = CircleShape, clip = true)
                    .clip(CircleShape)
                    .background(Color.White)
                    .size(Dp50)
                    .border(
                        width = getUnderLineHeight(focusState, placeholder, value),
                        color = getUnderLine(focusState, textFieldStatus),
                        shape = CircleShape
                    )
                    .constrainAs(circleContainer) {
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                        start.linkTo(parent.start)
                    }, contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(startIcon ?: R.drawable.default_icon),
                    contentDescription = "",
                    modifier = Modifier.size(24.dp)
                )
            }
        }
    }
}
