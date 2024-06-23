package com.example.heavyduty.presentation.view.util.customTextField

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import com.example.heavyduty.presentation.view.theme.HeavyDutyTheme


@Composable
fun CustomTextField(
    modifier: Modifier = Modifier,
    value: String = "",
    onValueChange: (String) -> Unit = {},
    leadingIcon: (@Composable () -> Unit)? = null,
    prefixText: (@Composable () -> Unit)? = null,
    trailingIcon: (@Composable () -> Unit)? = null,
    placeholderText: String = "Placeholder",
    placeholderColor: Color = MaterialTheme.colorScheme.onBackground,
    placeholderStyle: TextStyle = MaterialTheme.typography.titleSmall,
    keyboardType: KeyboardType = KeyboardType.Text,
    imeAction: ImeAction = ImeAction.Search,
    fontColor: Color = MaterialTheme.colorScheme.onBackground,
    fontSize: TextUnit = MaterialTheme.typography.titleSmall.fontSize,
    textPlacementAlignment: Alignment.Vertical = Alignment.CenterVertically,
    textPosition: Alignment = Alignment.TopStart,
    textAlign: TextAlign = TextAlign.Center,
    singleLine: Boolean = false,
    keyboardActions: KeyboardActions = KeyboardActions()
) {

    BasicTextField(
        keyboardActions = keyboardActions ,
        keyboardOptions = KeyboardOptions(
            keyboardType = keyboardType,
            imeAction = imeAction),
        modifier = modifier
            .background(
                color = MaterialTheme.colorScheme.background,
                shape = MaterialTheme.shapes.medium,
            )
            .fillMaxWidth(),
        value = value,
        onValueChange = { onValueChange(it) },
        singleLine = singleLine,
        cursorBrush = SolidColor(MaterialTheme.colorScheme.primary),
        textStyle = TextStyle(
            textAlign = textAlign,
            fontSize = fontSize,
            color = fontColor,
            fontFamily = MaterialTheme.typography.titleSmall.fontFamily),
        decorationBox = { innerTextField ->
            Row(
                modifier
                    .padding(start = 10.dp, end = 10.dp),
                verticalAlignment = textPlacementAlignment,
                horizontalArrangement = Arrangement.Center
            ) {
                if (leadingIcon != null) leadingIcon()
                Box(
                    Modifier
                        .weight(1f)
                        .padding(5.dp),
                    contentAlignment = textPosition) {
                    if (value.isEmpty())
                        Text(
                            text = placeholderText,
                            color = placeholderColor,
                            style = placeholderStyle
                        )
                    innerTextField()

                }
                if (trailingIcon != null) trailingIcon()
                if (prefixText != null) prefixText()
            }
        }
    )
}

@Preview
@Composable
private fun CustomTextFieldPreview(){
    CustomTextField()
}