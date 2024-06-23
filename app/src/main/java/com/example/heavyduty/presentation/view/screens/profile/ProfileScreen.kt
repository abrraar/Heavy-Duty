package com.example.heavyduty.presentation.view.screens.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.example.heavyduty.presentation.view.theme.HeavyDutyTheme
import com.example.heavyduty.presentation.view.theme.Shape
import com.example.heavyduty.presentation.view.util.customCard.CustomCard

@Composable
fun ProfileScreen(modifier: Modifier = Modifier){
    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .background(color = MaterialTheme.colorScheme.background),
        horizontalAlignment = Alignment.CenterHorizontally
    )
    {
        ConstraintLayout() {
            val (title, description) = createRefs()
            Box(
                modifier = Modifier
                    .fillMaxWidth(1f)
                    .height(150.dp)
                    .background(
                        shape = RoundedCornerShape(bottomEnd = 20.dp, bottomStart = 20.dp),
                        color = MaterialTheme.colorScheme.secondary
                    )
                    .constrainAs(title) {

                    }
            ) {

            }
            Box(modifier = Modifier
                .height(200.dp)
                .width(200.dp)
                .shadow(elevation = 20.dp, shape = RoundedCornerShape(100.dp))
                .background(color = Color.White, shape = RoundedCornerShape(100.dp))
                .constrainAs(description) {
                    top.linkTo(title.top, margin = 50.dp)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    bottom.linkTo(parent.bottom, margin = 30.dp)
                }){

            }
        }

        CustomCard(
            header = "Profile Information"
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth(1f)
                    .padding(start = 10.dp),
                horizontalAlignment = Alignment.Start
            ) {
                Spacer(modifier = Modifier.padding(top = 10.dp))
                Text(text = "Name", style = MaterialTheme.typography.titleSmall, modifier = Modifier.padding(bottom = 5.dp))
                Text(text = "Muhammad Al Abrar Amjad", style = MaterialTheme.typography.titleSmall)

                Text(text = "Email Address", style = MaterialTheme.typography.titleSmall, modifier = Modifier.padding(top = 15.dp, bottom = 5.dp))
                Text(text = "mohammadalabraramjad@gmail.com", style = MaterialTheme.typography.titleSmall)

                Text(text = "Password", style = MaterialTheme.typography.titleSmall, modifier = Modifier.padding(top = 15.dp, bottom = 5.dp))
                Text(text = "*************", style = MaterialTheme.typography.titleSmall)
                Spacer(modifier = Modifier.padding(bottom = 10.dp))

            }
        }

        CustomCard(
            modifier = Modifier.padding(top = 20.dp),
            header = "Current Body Profile"
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth(1f)
                    .padding(start = 10.dp),
                horizontalAlignment = Alignment.Start
            ) {
                Spacer(modifier = Modifier.padding(top = 10.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(text = "Height", style = MaterialTheme.typography.titleSmall, modifier = Modifier.padding(bottom = 5.dp,))
                    Text(text = "170 cm", style = MaterialTheme.typography.titleSmall, modifier = Modifier.padding(end = 5.dp,))
                }
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(text = "Weight", style = MaterialTheme.typography.titleSmall, modifier = Modifier.padding(bottom = 5.dp,))
                    Text(text = "80 kg", style = MaterialTheme.typography.titleSmall, modifier = Modifier.padding(end = 5.dp,))
                }

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(text = "Body fat", style = MaterialTheme.typography.titleSmall, modifier = Modifier.padding(bottom = 5.dp,))
                    Text(text = "20 %", style = MaterialTheme.typography.titleSmall, modifier = Modifier.padding(end = 5.dp,))
                }



            }
        }


    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun ProfileScreenPreview(){
    HeavyDutyTheme(dynamicColor = false) {
        ProfileScreen()
    }

}