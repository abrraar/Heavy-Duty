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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.heavyduty.R
import com.example.heavyduty.presentation.view.theme.HeavyDutyTheme

@Composable
fun CustomChipButton(
    text: String = "value",
    isSelected: Boolean = false,
    onValueChange: (String) -> Unit,
)
{
    Surface(
        shadowElevation = 0.dp,
        color = MaterialTheme.colorScheme.primary.copy(alpha = if(isSelected){ 1f } else { 0.5f }),
        modifier = Modifier
            .padding(start = 15.dp)
            .height(40.dp)
            .width(120.dp),
        shape = RoundedCornerShape(20.dp)
    ) {
        Row(
            modifier = Modifier.toggleable(
                value = isSelected,
                onValueChange = { onValueChange(text) }
            ),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = text,
                style = MaterialTheme.typography.labelSmall)
        }
    }
}

@Preview
@Composable
private fun CustomChipButtonPreview(){
    CustomChipButton(
        onValueChange = {}
    )
}