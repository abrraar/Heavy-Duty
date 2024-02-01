package com.example.heavyduty.presentation.view.util.customButton

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.selection.toggleable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.heavyduty.R
import com.example.heavyduty.presentation.view.theme.HeavyDutyTheme

@Composable
fun CustomChipButton(
    text: Int = R.string.value,
    isSelected: Boolean = false,
    onValueChange: ((Boolean) -> Unit)? = null,
)
{
    Surface(
        shadowElevation = 10.dp,
        color = MaterialTheme.colorScheme.primary,
        modifier = Modifier
            .padding(start = 15.dp)
            .height(40.dp)
            .width(120.dp),
        shape = RoundedCornerShape(20.dp)
    ) {
        Row(
            modifier = Modifier.toggleable(
                value = isSelected,
                onValueChange = onValueChange!!
            ),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = stringResource(id = text),
                style = MaterialTheme.typography.labelSmall)
        }
    }
}

@Preview
@Composable
private fun CustomChipButtonPreview(){
    HeavyDutyTheme(dynamicColor = false) {
        CustomChipButton()
    }
}