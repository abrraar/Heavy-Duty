package com.example.heavyduty.presentation.view.util.prompts

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.heavyduty.presentation.view.theme.Green
import com.example.heavyduty.presentation.view.theme.HeavyDutyTheme
import com.example.heavyduty.presentation.view.theme.Red
import com.example.heavyduty.presentation.view.theme.Shape
import com.example.heavyduty.presentation.view.theme.dimens

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Prompt(
    titleText: String = "Title Text",
    message: String = "Message",
    confirmButton: Boolean = true,
    onConfirm: (() -> Unit)? = null,
    cancelButton: Boolean = true,
    onCancel: (() -> Unit)? = null
){
    BasicAlertDialog(onDismissRequest = onCancel!!)
    {
        Column(
            modifier = Modifier
                .height(MaterialTheme.dimens.promptHeight)
                .width(MaterialTheme.dimens.promptWidth)
                .background(
                    shape = Shape.small,
                    color = MaterialTheme.colorScheme.secondary
                ),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        )
        {
            Row(
                modifier = Modifier
                    .height(40.dp)
                    .fillMaxWidth(1f)
                    .background(
                        shape = RoundedCornerShape(topEnd = 20.dp, topStart = 20.dp),
                        color = MaterialTheme.colorScheme.primary
                    ),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center

            ) {
                Text(
                    text = titleText,
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.onPrimary
                )
            }

            Column(
                modifier = Modifier
                    .fillMaxWidth(1f)
                    .height(100.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(15.dp),
                    text = message,
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.onPrimary
                )
            }
            if (confirmButton && cancelButton) {
                LazyRow(
                    modifier = Modifier
                        .fillMaxWidth(1f)
                        .fillMaxHeight(1f)
                ) {
                    items(2) {
                        Column(
                            modifier = Modifier
                                .clickable(
                                    onClick = when (it) {
                                        0 -> onCancel
                                        1 -> onConfirm!!
                                        else -> {
                                            {}
                                        }
                                    }
                                )
                                .background(
                                    color = when (it) {
                                        0 -> Red
                                        1 -> Green
                                        else -> Color.Gray
                                    },
                                    shape = when (it) {
                                        0 -> RoundedCornerShape(bottomStart = 20.dp)
                                        1 -> RoundedCornerShape(bottomEnd = 20.dp)
                                        else -> Shape.small
                                    }
                                )
                                .height(MaterialTheme.dimens.promptBtnHeight)
                                .width(MaterialTheme.dimens.promptBtnWidth),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        ) {
                            Text(
                                text = when (it) {
                                    0 -> "Cancel"
                                    1 -> "Confirm"
                                    else -> ""
                                },
                                style = MaterialTheme.typography.titleLarge,
                                color = MaterialTheme.colorScheme.onPrimary
                            )
                        }
                    }
                }
            } else if (confirmButton) {
                Button(
                    onClick = onConfirm!!,
                    color = Green,
                    text = "Confirm"
                )
            } else if (cancelButton) {
                Button(
                    onClick = onCancel,
                    color = Red,
                    text = "Cancel"
                )
            }
        }
    }
}


@Composable
private fun Button(
    onClick: () -> Unit,
    color: Color,
    text: String,
){
    Column(
        modifier = Modifier
            .clickable(onClick = onClick)
            .background(
                color = color,
                shape = RoundedCornerShape(bottomStart = 20.dp, bottomEnd = 20.dp)
            )
            .height(60.dp)
            .width(300.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.onPrimary)
    }

}


@Preview
@Composable
private fun PromptPreview(){
    HeavyDutyTheme(dynamicColor = false) {
        Prompt(onCancel = {}, onConfirm = {})
    }
}