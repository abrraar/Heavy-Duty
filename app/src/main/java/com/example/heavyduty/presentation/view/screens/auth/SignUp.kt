package com.example.heavyduty.presentation.view.screens.auth

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.heavyduty.R
import com.example.heavyduty.navigation.NavigationScreenNames
import com.example.heavyduty.presentation.view.theme.Facebook
import com.example.heavyduty.presentation.view.theme.HeavyDutyTheme
import com.example.heavyduty.presentation.view.theme.dimens
import com.example.heavyduty.presentation.view.util.customButton.CustomButton
import com.example.heavyduty.presentation.view.util.customTextField.CustomTextField

@Composable
fun SignUp(navHostController: NavHostController)
{
    Column(
        modifier = Modifier
            .background(color = MaterialTheme.colorScheme.secondary)
            .fillMaxWidth(1f)
            .fillMaxHeight(1f),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ){
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                modifier = Modifier.padding(
                    bottom = MaterialTheme.dimens.small3,
                    top = MaterialTheme.dimens.medium3),
                text = "Sign in",
                style = MaterialTheme.typography.headlineMedium,
                color = MaterialTheme.colorScheme.onPrimary
            )
            Text(
                modifier = Modifier,
                text = "Create a new account",
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.onPrimary
            )
        }

        Column(
            modifier = Modifier.padding(MaterialTheme.dimens.medium2),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.Start
        ){
            // username
            Text(
                modifier = Modifier.padding(
                    bottom = MaterialTheme.dimens.small1,
                    top = MaterialTheme.dimens.medium1
                ),
                text = "Username",
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.onPrimary
            )
            CustomTextField(
                imeAction = ImeAction.Next,
                textAlign = TextAlign.Start,
                placeholderText = "Enter your name here",
                modifier = Modifier.height(MaterialTheme.dimens.large2)
            )
            // email
            Text(
                modifier = Modifier.padding(
                    bottom = MaterialTheme.dimens.small1,
                    top = MaterialTheme.dimens.medium1
                ),
                text = "Email Address",
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.onPrimary
            )
            CustomTextField(
                imeAction = ImeAction.Next,
                textAlign = TextAlign.Start,
                modifier = Modifier.height(MaterialTheme.dimens.large2),
                placeholderText = "Enter email address here"
            )
            // password
            Text(
                modifier = Modifier.padding(
                    bottom = MaterialTheme.dimens.small1,
                    top = MaterialTheme.dimens.medium1
                ),
                text = "Password",
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.onPrimary
            )
            CustomTextField(
                imeAction = ImeAction.Done,
                textAlign = TextAlign.Start,
                placeholderText = "Enter your password here",
                modifier = Modifier.height(MaterialTheme.dimens.large2)
            )
        }
        CustomButton(
            modifier = Modifier
                .height(MaterialTheme.dimens.mediumButtonHeight)
                .width(MaterialTheme.dimens.mediumButtonWidth),
            text = "Sign in",
            onClick = { navHostController.navigate(NavigationScreenNames.MainScreens.route)  },
            style = MaterialTheme.typography.titleLarge
        )

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            Column(
                modifier = Modifier.fillMaxHeight(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top)
            {
                // facebook
                CustomButton(
                    leadingIcon = {
                        Icon(
                            tint = Color.Unspecified,
                            painter = painterResource(id = R.drawable.facebook_icn),
                            contentDescription = null
                        )
                    },
                    modifier = Modifier
                        .padding(
                            bottom = MaterialTheme.dimens.medium1,
                            top = MaterialTheme.dimens.medium3
                        )
                        .width(MaterialTheme.dimens.largeButtonWidth)
                        .height(MaterialTheme.dimens.largeButtonHeight),
                    text = "Sign in with Facebook",
                    onClick = { /*TODO*/ },
                    backgroundColor = Facebook,
                    textColor = Color.White,
                    style = MaterialTheme.typography.titleSmall
                )

                // google
                CustomButton(
                    leadingIcon = {
                        Icon(
                            tint = Color.Unspecified,
                            painter = painterResource(id = R.drawable.google_icn),
                            contentDescription = null
                        )
                    },
                    modifier = Modifier
                        .width(MaterialTheme.dimens.largeButtonWidth)
                        .height(MaterialTheme.dimens.largeButtonHeight),
                    text = "Sign in with Google",
                    onClick = { /*TODO*/ },
                    backgroundColor = Color.White,
                    textColor = Color.Black,
                    style = MaterialTheme.typography.titleSmall
                )

                // login
                Text(
                    modifier = Modifier.padding(bottom = 15.dp, top = 30.dp),
                    text = "Already have an account ?",
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.onPrimary
                )
                Text(
                    modifier = Modifier
                        .clickable(onClick = { navHostController.navigate(NavigationScreenNames.Login.route) })
                        .padding(bottom = 10.dp,),
                    text = "Log in",
                    style = MaterialTheme.typography.headlineSmall,
                    color = MaterialTheme.colorScheme.primary
                )
            }
        }
    }
}



@Preview
@Composable
private fun SignUpPreview(){
    HeavyDutyTheme(dynamicColor = false) {
        SignUp(navHostController = rememberNavController())
    }
}