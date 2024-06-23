package com.example.heavyduty.presentation.view.util.searchBars

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.heavyduty.R
import com.example.heavyduty.presentation.view.theme.IntractableBackgroundColor

@Composable
fun SearchBar(
    value: String  = "",
    onValueChange: (String) -> Unit = {},
    addClickable: (() -> Unit)? = null,
    recycleBinClickable: (() -> Unit)? = null,
    filterClickable: (() -> Unit)? = null,
    placeholderText: String = "Search"

){
    Row(
        modifier = Modifier
            .fillMaxWidth(1f)
            .height(60.dp)
            .background(color = Color(0xFF000000))
            .padding(top = 15.dp, bottom = 15.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        TextField(
            value = value,
            onValueChange = onValueChange,
            leadingIcon = {
                Icon(
                    painter = painterResource(id = R.drawable.search_icn),
                    contentDescription = null)
            },
            colors = TextFieldDefaults.colors(
                disabledTextColor = Color.Black,
                focusedContainerColor = Color.White,
                unfocusedContainerColor = Color.White,
                disabledContainerColor = Color.White,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent,
            ),
            shape = RoundedCornerShape(20.dp),
            modifier = Modifier
                .width(220.dp),
            placeholder = {
                Text(
                    text = placeholderText,
                    color = IntractableBackgroundColor,
                    style = MaterialTheme.typography.labelSmall
                )},
            singleLine = true,
            textStyle = LocalTextStyle.current.copy(textAlign = TextAlign.Left),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Search
            )
        )

        if (addClickable != null) {
            IconButton(onClick = addClickable) {
                Icon(
                    painter =
                    painterResource(
                        id = R.drawable.add_icn),
                    contentDescription = null,
                    tint = Color.White
                )
            }
        }

        if (filterClickable != null) {
            IconButton(onClick = filterClickable) {
                Icon(
                    painter =
                    painterResource(
                        id = R.drawable.filter_icn),
                    contentDescription = null,
                    tint = Color.White
                )
            }
        }

        if (recycleBinClickable != null) {
            IconButton(onClick = recycleBinClickable) {
                Icon(
                    painter =
                    painterResource(
                        id = R.drawable.recycle_bin_icn),
                    contentDescription = null,
                    tint = Color.White
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SearchBarPreview(){
    SearchBar(
        onValueChange = {},
        addClickable = {},
        filterClickable = {},
        recycleBinClickable = {})
}