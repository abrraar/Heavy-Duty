package com.example.heavyduty.presentation.view.screens.tracker.bodyComposition.addBodyComposition

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
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
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.heavyduty.R
import com.example.heavyduty.data.local.tracker.bodyComposition.addBodyComposition.AddBodyCompositionTexts.promptText
import com.example.heavyduty.presentation.view.util.customButton.CustomButton
import com.example.heavyduty.presentation.view.theme.Black
import com.example.heavyduty.presentation.view.theme.HeavyDutyTheme
import com.example.heavyduty.presentation.viewModel.tracker.bodyComposition.addBodyComposition.AddBodyCompositionViewModel
import com.example.heavyduty.presentation.view.util.customCard.CustomCard
import com.example.heavyduty.presentation.view.util.customTextField.CustomTextField


@Composable
fun BodyCompositionAddManually(){
    val viewModel: AddBodyCompositionViewModel = hiltViewModel()
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth(1f)
            .fillMaxHeight(1f)
            .background(color = MaterialTheme.colorScheme.background)
        )
    {

        Spacer(modifier = Modifier.padding(15.dp))
        CustomButton(
            onClick = { viewModel.setPhysicalTraitToTrue() },
            text = stringResource(id = R.string.enter_physical_trait))

        if (viewModel.state.value.showPhysicalTraitPrompt){
            AddComponentsPrompt(onCancel = { viewModel.setPhysicalTraitToFalse() }, viewModel = viewModel)
        }

        if(viewModel.state.value.isWeightClicked){
            WeightCard()
        }
    }
}

@Composable
private fun WeightCard(){
    CustomCard(
        modifier = Modifier.height(200.dp),
        header = stringResource(id = R.string.weight)
    ) {
        Text(
            modifier = Modifier.padding(bottom = 15.dp, top = 10.dp),
            text = stringResource(id = R.string.enter_weight),
            style = MaterialTheme.typography.titleSmall)

        CustomTextField(
            prefixText =  {
                Text(
                    text = "Kg",
                    color = MaterialTheme.colorScheme.onBackground,
                    style = MaterialTheme.typography.titleSmall)
                          },
            singleLine = true,
            placeholderText = "Click here to type...",
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

@Composable
private fun AddComponentsPrompt(
    onCancel: () -> Unit,
    viewModel: AddBodyCompositionViewModel
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
                        onClick = {
                                  when(promptText.indexOf(texts)){
                                      0 -> if(viewModel.state.value.isWeightClicked)
                                      { viewModel.setWeightToFalse()}
                                      else{ viewModel.setWeightToTrue() }

                                      1 -> if(viewModel.state.value.isHeightClicked)
                                      { viewModel.setHeightToFalse()}
                                      else{ viewModel.setWeightToTrue() }

                                      2 -> if(viewModel.state.value.isBodyFatClicked)
                                      { viewModel.setBodyFatToFalse()}
                                      else{ viewModel.setBodyFatToTrue() }

                                      3 -> if(viewModel.state.value.isMuscleMassClicked)
                                      { viewModel.setMuscleMassToFalse()}
                                      else{ viewModel.setWeightToTrue() }
                                  }
                        },
                        style = MaterialTheme.typography.titleSmall,
                        text = stringResource(id = texts),
                        modifier = Modifier
                            .width(200.dp)
                            .padding(bottom = 15.dp),
                        backgroundColorAlphaValue = when(promptText.indexOf(texts))
                        {
                            0 -> if(viewModel.state.value.isWeightClicked){ 1f }else{0.5f}
                            1 -> if(viewModel.state.value.isHeightClicked){ 1f }else{0.5f}
                            2 -> if(viewModel.state.value.isBodyFatClicked){ 1f }else{0.5f}
                            3 -> if(viewModel.state.value.isMuscleMassClicked){ 1f }else{0.5f}
                            else -> {1f}
                        }
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
        WeightCard()
    }

}

@Preview(apiLevel = 33)
@Composable
private fun AddComponentsPromptPreview(){
    HeavyDutyTheme(dynamicColor = false) {
        val viewModel: AddBodyCompositionViewModel = hiltViewModel()
        AddComponentsPrompt(onCancel = {}, viewModel)
    }

}

@Preview(apiLevel = 33)
@Composable
private  fun BodyCompositionAddManuallyPreview(){
    HeavyDutyTheme(dynamicColor = false) {
        BodyCompositionAddManually()
    }
}