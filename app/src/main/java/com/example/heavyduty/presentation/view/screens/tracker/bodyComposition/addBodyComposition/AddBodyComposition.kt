package com.example.heavyduty.presentation.view.screens.tracker.bodyComposition.addBodyComposition

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Snackbar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.example.heavyduty.R
import com.example.heavyduty.data.local.Constants
import com.example.heavyduty.data.local.tracker.bodyComposition.addBodyComposition.AddBodyCompositionTexts.promptText
import com.example.heavyduty.presentation.view.util.customButton.CustomButton
import com.example.heavyduty.presentation.view.theme.Black
import com.example.heavyduty.presentation.view.theme.Green
import com.example.heavyduty.presentation.view.theme.HeavyDutyTheme
import com.example.heavyduty.presentation.view.theme.Shape
import com.example.heavyduty.presentation.view.util.customTextField.CustomTextField
import com.example.heavyduty.presentation.viewModel.tracker.bodyComposition.addBodyComposition.AddBodyCompositionEvents
import com.example.heavyduty.presentation.viewModel.tracker.bodyComposition.addBodyComposition.AddBodyCompositionUIState


@Composable
fun AddBodyComposition(
    addBodyCompositionUIState: AddBodyCompositionUIState,
    addBodyCompositionEvents: ((AddBodyCompositionEvents) -> Unit)? = null
)
{
    if (addBodyCompositionUIState.showPhysicalTraitPrompt){
        AddComponentsPrompt(
            onCancel = { addBodyCompositionEvents!!(AddBodyCompositionEvents.EnterPhysicalTraitClicked(false)) },
            addBodyCompositionEvents = addBodyCompositionEvents,
            addBodyCompositionUIState = addBodyCompositionUIState)
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth(1f)
            .fillMaxHeight(1f)
            .background(color = MaterialTheme.colorScheme.background)
        )
    {

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .fillMaxWidth(1f)
        ) {
            CustomButton(
                modifier = Modifier
                    .padding(bottom = 20.dp, top = 20.dp)
                    .width(300.dp),
                text = stringResource(id = R.string.enter_physical_trait),
                textColor = MaterialTheme.colorScheme.onPrimary,
                style = MaterialTheme.typography.titleSmall,
                onClick = {
                    addBodyCompositionEvents!!(AddBodyCompositionEvents.EnterPhysicalTraitClicked(true))
                }
            )

            if(addBodyCompositionUIState.bodyCompositionList.isNotEmpty()){
                for(it in addBodyCompositionUIState.bodyCompositionList){
                    BodyCompositionCard(
                        title = when(it) {
                            Constants.WEIGHT -> R.string.weight
                            Constants.HEIGHT -> R.string.height
                            Constants.BODYFAT -> R.string.body_fat
                            Constants.MUSCLEMASS ->  R.string.muscle_mass
                            else -> R.string.weight
                        },
                        value = when(it) {
                            Constants.WEIGHT -> addBodyCompositionUIState.weightValue.value
                            Constants.HEIGHT -> addBodyCompositionUIState.heightValue.value
                            Constants.BODYFAT -> addBodyCompositionUIState.bodyFatValue.value
                            Constants.MUSCLEMASS -> addBodyCompositionUIState.muscleMassValue.value
                            else -> "" },
                        onValueChange = {
                                text ->
                            when(it) {
                                Constants.WEIGHT -> addBodyCompositionUIState.weightValue.value = text
                                Constants.HEIGHT -> addBodyCompositionUIState.heightValue.value = text
                                Constants.BODYFAT -> addBodyCompositionUIState.bodyFatValue.value = text
                                Constants.MUSCLEMASS -> addBodyCompositionUIState.muscleMassValue.value = text
                                else -> addBodyCompositionUIState.muscleMassValue.value = text
                            }
                        },
                        prefixText = when(it) {
                            Constants.WEIGHT -> R.string.kg
                            Constants.HEIGHT -> R.string.cm
                            Constants.BODYFAT -> R.string.percent
                            Constants.MUSCLEMASS ->  R.string.percent
                            else -> R.string.weight
                        },
                        description = when(it){
                            Constants.WEIGHT -> R.string.enter_weight
                            Constants.HEIGHT -> R.string.enter_height
                            Constants.BODYFAT -> R.string.enter_body_fat
                            Constants.MUSCLEMASS ->  R.string.enter_muscle_mass
                            else -> R.string.weight
                        }
                    )
                    Spacer(modifier = Modifier.padding(15.dp))
                }
            }
        }

        if (addBodyCompositionUIState.bodyCompositionList.isEmpty()){
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxWidth(1f)
                    .weight(1f)
            ) {
                Text(
                    text = "No physical trait selected",
                    style = MaterialTheme.typography.titleSmall,
                    color = MaterialTheme.colorScheme.onBackground
                )
            }
        }
        if(addBodyCompositionUIState.weightValue.value != ""){
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .height(80.dp)
                    .clickable {
                        addBodyCompositionEvents!!(
                            AddBodyCompositionEvents.SaveButtonClicked(
                                weight = addBodyCompositionUIState.weightValue.value,
                                height = addBodyCompositionUIState.heightValue.value,
                                bodyfat = addBodyCompositionUIState.bodyFatValue.value,
                                musclemass = addBodyCompositionUIState.muscleMassValue.value
                            )
                        )

                    }
                    .fillMaxWidth(1f)
                    .background(color = Green))
            {
                Text(
                    text = "Save",
                    style = MaterialTheme.typography.headlineSmall,
                    color = MaterialTheme.colorScheme.onPrimary
                )
            }
        }

    }
}

@Composable
private fun BodyCompositionCard(
    title: Int = R.string.weight,
    value: String = "",
    prefixText: Int = R.string.kg,
    description: Int = R.string.enter_weight,
    onValueChange: (String) -> Unit = {},
){
    Card(
        modifier = Modifier
            .width(340.dp)
            .height(IntrinsicSize.Max),
        shape = Shape.small,
        colors = CardDefaults.cardColors(Black),
        elevation = CardDefaults.cardElevation(25.dp),
    ) {
        Column(modifier = Modifier
            .background(MaterialTheme.colorScheme.primary)
            .fillMaxWidth()
            .height(50.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center)
        {
            Text(
                text = stringResource(id = title),
                color = MaterialTheme.colorScheme.onPrimary,
                style = MaterialTheme.typography.titleLarge)
        }
        Column(
            modifier = Modifier
                .fillMaxWidth(1f)
                .padding(bottom = 15.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                modifier = Modifier.padding(bottom = 15.dp, top = 10.dp),
                text = stringResource(id = description),
                style = MaterialTheme.typography.titleSmall)

            CustomTextField(
                value = value,
                onValueChange = onValueChange,
                prefixText =  {
                    Text(
                        text = stringResource(id = prefixText),
                        color = MaterialTheme.colorScheme.onBackground,
                        style = MaterialTheme.typography.titleSmall)
                },
                singleLine = true,
                placeholderText = "Click here to type",
                textPosition = Alignment.Center,
                modifier = Modifier
                    .height(80.dp)
                    .width(280.dp),
                textPlacementAlignment = Alignment.CenterVertically,
                imeAction = ImeAction.Done,
                keyboardType = KeyboardType.Number,
                placeholderStyle = MaterialTheme.typography.headlineSmall,
                fontColor = MaterialTheme.colorScheme.onPrimary,
                fontSize = MaterialTheme.typography.headlineLarge.fontSize
            )
        }
        
    }
}

@Composable
private fun AddComponentsPrompt(
    onCancel: () -> Unit,
    addBodyCompositionUIState: AddBodyCompositionUIState,
    addBodyCompositionEvents: ((AddBodyCompositionEvents) -> Unit)? = null
){

    Dialog(onDismissRequest = { onCancel() }) {
        Box(modifier = Modifier
            .background(color = Black, shape = RoundedCornerShape(20.dp))
            .width(300.dp),
        ){
            Row(
                modifier = Modifier.fillMaxWidth(1f),
                verticalAlignment = Alignment.Top,
                horizontalArrangement = Arrangement.End
            ) {
                IconButton(onClick = onCancel) {
                    Icon(
                        painter = painterResource(id = R.drawable.delete_icn),
                        contentDescription = null,
                        tint = Color.White
                    )
                }
            }
            Column(
                modifier = Modifier
                    .height(320.dp)
                    .fillMaxWidth(1f),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(text = "Tap to select",
                    modifier = Modifier.padding(bottom= 20.dp),
                    color = MaterialTheme.colorScheme.onPrimary,
                    style = MaterialTheme.typography.headlineSmall)

                for(texts in promptText)
                {
                    CustomButton(
                        modifier = Modifier
                            .width(200.dp)
                            .padding(bottom = 15.dp),
                        text = stringResource(id = texts),
                        onClick = {
                            when(promptText.indexOf(texts)){
                                0 -> {
                                    addBodyCompositionEvents!!(AddBodyCompositionEvents.PhysicalTraitClicked(Constants.WEIGHT))
                                }
                                1 -> {
                                    addBodyCompositionEvents!!(AddBodyCompositionEvents.PhysicalTraitClicked(Constants.HEIGHT))
                                }
                                2 -> {
                                    addBodyCompositionEvents!!(AddBodyCompositionEvents.PhysicalTraitClicked(Constants.BODYFAT))
                                }
                                3 -> {
                                    addBodyCompositionEvents!!(AddBodyCompositionEvents.PhysicalTraitClicked(Constants.MUSCLEMASS))
                                }
                            }
                        },
                        backgroundColorAlphaValue = when(promptText.indexOf(texts)) {
                            0 -> {
                                if (addBodyCompositionUIState.isWeightClicked) {
                                    1f
                                } else {
                                    0.5f
                                }
                            }
                            1 -> {
                                if (addBodyCompositionUIState.isHeightClicked) {
                                    1f
                                } else {
                                    0.5f
                                }
                            }
                            2 -> {
                                if (addBodyCompositionUIState.isBodyFatClicked) {
                                    1f
                                } else {
                                    0.5f
                                }
                            }
                            3 -> {
                                if (addBodyCompositionUIState.isMuscleMassClicked) {
                                    1f
                                } else {
                                    0.5f
                                }
                            }
                            else -> {
                                1f
                            }
                        },
                        style = MaterialTheme.typography.titleSmall
                    )
                }

            }
        }
    }
}

@Preview(apiLevel = 33)
@Composable
private fun WeightCardPreview(){
    HeavyDutyTheme(dynamicColor = false) {
        BodyCompositionCard()
    }

}

@Preview(apiLevel = 33)
@Composable
private fun AddComponentsPromptPreview(){
    HeavyDutyTheme(dynamicColor = false) {
        AddComponentsPrompt(onCancel = {},  addBodyCompositionUIState = AddBodyCompositionUIState())
    }

}

@Preview(apiLevel = 33)
@Composable
private  fun BodyCompositionAddManuallyPreview(){
    HeavyDutyTheme(dynamicColor = false) {
        AddBodyComposition( addBodyCompositionUIState =  AddBodyCompositionUIState())
    }
}