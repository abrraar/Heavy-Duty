package com.example.heavyduty.presentation.view.util.customCard

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.heavyduty.R
import com.example.heavyduty.presentation.view.theme.Shape
import com.example.heavyduty.presentation.view.theme.Black
import com.example.heavyduty.presentation.view.theme.HeavyDutyTheme
import com.example.heavyduty.presentation.view.theme.dimens


@Composable
fun CustomCard(
    modifier: Modifier = Modifier,
    enableDeleteBtn: Boolean = false,
    deleteBtn: (() -> Unit)? = null,
    alignment: Alignment.Horizontal = Alignment.CenterHorizontally,
    arrangement: Arrangement.Vertical = Arrangement.Top,
    header: String = "Header",
    backGroundColor: CardColors = CardDefaults.cardColors(Black),
    textAlign: Alignment.Horizontal = Alignment.CenterHorizontally,
    composable: (@Composable () -> Unit)? = null
    ){
    Card(
        modifier = modifier
            .width(MaterialTheme.dimens.cardWidth),
        shape = Shape.small,
        colors = backGroundColor,
        elevation = CardDefaults.cardElevation(25.dp)

    ) {
        Column(modifier = Modifier
            .background(MaterialTheme.colorScheme.primary)
            .fillMaxWidth()
            .height(50.dp),
            horizontalAlignment = textAlign,
            verticalArrangement = Arrangement.Center)
        {
            Row(
                modifier = Modifier.fillMaxWidth(1f),
                verticalAlignment = Alignment.CenterVertically
            )
            {
                Row(modifier.weight(1f)) {
                    Text(
                        modifier = Modifier.padding(start = 15.dp, end = 15.dp),
                        text = header,
                        color = MaterialTheme.colorScheme.onPrimary,
                        style = MaterialTheme.typography.titleLarge)
                }
                Row(
                    modifier.weight(1f).fillMaxHeight(1f).padding(end = 20.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.End)
                {
                    if(enableDeleteBtn){
                        Icon(
                            modifier = Modifier.clickable(onClick = deleteBtn!!),
                            painter = painterResource(id = R.drawable.delete_icn),
                            contentDescription = null)
                    }
                }
            }
        }
        Column(
            modifier = Modifier
                .fillMaxWidth(1f)
                .padding(10.dp),
            horizontalAlignment = alignment,
            verticalArrangement = arrangement
        ) {
            if (composable != null){
                composable()
            }
        }


    }
}

@Preview
@Composable
private fun CustomCardPreview(){
    CustomCard()
}