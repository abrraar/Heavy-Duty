package com.example.heavyduty.presentation.view.util.filter

import android.database.ContentObservable
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.heavyduty.R
import com.example.heavyduty.presentation.view.theme.HeavyDutyTheme
import com.example.heavyduty.presentation.view.util.customButton.CustomChipButton

private val orderByTexts = arrayListOf("Ascending","Descending")

@Composable
fun Filter(
    showComponent: Boolean = false,
    components: List<String> = emptyList(),
    componentIsSelected: Boolean = false,
    componentOnSelectedCategoryChange: ((String) -> Unit)? = null,

    showGroupBy: Boolean = false,
    groupBy: List<String> = emptyList(),
    groupByIsSelected: Boolean = false,
    groupByOnSelectedCategoryChange: ((String) -> Unit)? = null,

    showOrderBy: Boolean = true,
    orderByObservable: String = "",
    orderByIsSelected: Boolean = false,
    orderByOnSelectedCategoryChange: (String) -> Unit,
){
    val header = remember { mutableStateListOf<Int>() }
    if(!showComponent) {
        if(header.contains(R.string.select_component)){ header.remove(R.string.select_component) }
    } else {
        if(!header.contains(R.string.select_component)){ header.add(R.string.select_component) }
    }
    if(!showGroupBy) {
        if(header.contains(R.string.group_by)){ header.remove(R.string.group_by)}
    } else {
        if(!header.contains(R.string.group_by)){
            header.add(R.string.group_by)
        }
    }
    if(!showOrderBy) {
        if(header.contains(R.string.order_by)){header.remove( R.string.order_by)}
    } else {
        if(!header.contains(R.string.order_by)){header.add( R.string.order_by )} }
    val height: Int = (header.size * 120)

    LazyColumn(
        modifier = Modifier
            .fillMaxWidth(1f)
            .height(
                height.dp
            )
    ){
        items(header.size)
        { it ->
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
                    shape = if (it == header.size.minus(1)) {
                        RoundedCornerShape(bottomStart = 20.dp, bottomEnd = 20.dp)
                    } else {
                        RoundedCornerShape(0.dp)
                    }
                ),
                verticalAlignment = Alignment.Top,
                horizontalArrangement = Arrangement.Start)
            {
                if(showComponent){
                    for(component in components){
                        CustomChipButton(
                            text = component,
                            isSelected = componentIsSelected,
                            onValueChange = {
                                componentOnSelectedCategoryChange
                            }

                        )
                    }
                }
                if(showGroupBy) {
                    for (component in groupBy) {
                        CustomChipButton(
                            text = component,
                            isSelected = groupByIsSelected,
                            onValueChange = {
                                groupByOnSelectedCategoryChange
                            }
                        )
                    }
                }
                if(showOrderBy){
                    for(orderBy in orderByTexts){
                        CustomChipButton(
                            text = orderBy,
                            isSelected = orderBy == orderByObservable,
                            onValueChange = {
                                onVal ->
                                orderByOnSelectedCategoryChange(onVal)
                            }
                        )
                    }
                }
            }

        }
    }
}

@Preview(showBackground = false)
@Composable
private fun FilterPreview(){
    Filter(
        orderByOnSelectedCategoryChange = {}
    )
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