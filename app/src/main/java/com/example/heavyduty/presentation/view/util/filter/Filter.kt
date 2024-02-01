package com.example.heavyduty.presentation.view.util.filter

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.ScrollableState
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.heavyduty.R
import com.example.heavyduty.presentation.view.theme.HeavyDutyTheme
import com.example.heavyduty.presentation.view.util.customButton.CustomChipButton

private val header = listOf(R.string.select_component, R.string.group_by, R.string.order_by)
private val orderByTexts = listOf(R.string.ascending, R.string.descending)

@Composable
fun Filter(
    components: List<Int> = emptyList(),
    componentIsSelected: Boolean = false,
    componentOnSelectedCategoryChange: ((String) -> Unit)? = null,
    componentOnExecuteSearch: (() -> Unit)? = null,

    groupBy: List<Int> = emptyList(),
    groupByIsSelected: Boolean = false,
    groupByOnSelectedCategoryChange: ((String) -> Unit)? = null,
    groupByOnExecuteSearch: (() -> Unit)? = null,

    orderByIsSelected: Boolean = false,
    orderByOnSelectedCategoryChange: ((String) -> Unit)? = null,
    orderByOnExecuteSearch: (() -> Unit)? = null,
){
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth(1f)
            .height(360.dp)
    ){
        items(header.size)
        {
            //------------------- Header --------------------
            Row(modifier = Modifier
                .fillMaxWidth(1f)
                .height(60.dp)
                .background(color = MaterialTheme.colorScheme.secondary),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start)
            {
                Text(
                    text = stringResource(id = header[it]),
                    color = MaterialTheme.colorScheme.onSecondary,
                    modifier = Modifier.padding(start = 15.dp),
                    style = MaterialTheme.typography.headlineSmall)
            }

            //---------------------- Chips ----------------------------
            Row(modifier = Modifier
                .fillMaxWidth(1f)
                .scrollable(orientation = Orientation.Horizontal, state = ScrollableState { 0f })
                .height(60.dp)
                .background(
                    color = MaterialTheme.colorScheme.secondary,
                    shape = if (it == 2) {
                        RoundedCornerShape(bottomStart = 20.dp, bottomEnd = 20.dp)
                    } else {
                        RoundedCornerShape(0.dp)
                    }
                ),
                verticalAlignment = Alignment.Top,
                horizontalArrangement = Arrangement.Start)
            {
                when(it){
                    0 -> for(component in components){
                        CustomChipButton(
                            text = component,
                            isSelected = componentIsSelected,
                            onValueChange = {
                                componentOnSelectedCategoryChange
                                componentOnExecuteSearch
                            }

                        )
                    }
                    1 -> for(component in groupBy){
                        CustomChipButton(
                            text = component,
                            isSelected = groupByIsSelected,
                            onValueChange = {
                                groupByOnSelectedCategoryChange
                                groupByOnExecuteSearch
                            }
                        )
                    }
                    2 -> for(orderBy in orderByTexts){
                        CustomChipButton(
                            text = orderBy,
                            isSelected = orderByIsSelected,
                            onValueChange = {
                                orderByOnSelectedCategoryChange
                                orderByOnExecuteSearch
                            }
                        )
                    }
                }
            }

        }
    }
}


@Preview(showBackground = true)
@Composable
private fun FilterPreview(){
    HeavyDutyTheme(dynamicColor = false) {
        Filter()
    }
}


//Row(modifier = Modifier
//.fillMaxWidth(1f)
//.height(60.dp)
//.background(color = MaterialTheme.colorScheme.secondary),
//verticalAlignment = Alignment.CenterVertically,
//horizontalArrangement = Arrangement.Start)
//{
//    Text(
//        text = stringResource(id = componentTitle[it]),
//        color = MaterialTheme.colorScheme.onSecondary,
//        modifier = Modifier.padding(start = 15.dp),
//        style = MaterialTheme.typography.headlineSmall)
//}