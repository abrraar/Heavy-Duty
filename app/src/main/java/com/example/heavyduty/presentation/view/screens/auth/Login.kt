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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
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
fun Login(navHostController: NavHostController)
{
    Column(
        modifier = Modifier
            .background(color = MaterialTheme.colorScheme.secondary)
            .fillMaxWidth(1f)
            .fillMaxHeight(1f),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ){
        // title
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                modifier = Modifier.padding(
                    bottom = MaterialTheme.dimens.small3,
                    top = MaterialTheme.dimens.medium3),
                text = "Log in",
                style = MaterialTheme.typography.headlineMedium,
                color = MaterialTheme.colorScheme.onPrimary
            )
            Text(
                textAlign = TextAlign.Center,
                modifier = Modifier,
                text = "Log in to your existing account",
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.onPrimary
            )
        }

        Column(
            modifier = Modifier.padding(30.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.Start
        ){
            // email address
            Text(
                modifier = Modifier.padding(
                    bottom = 10.dp,
                    top = 25.dp
                ),
                text = "Email Address",
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.onPrimary
            )
            CustomTextField(
                textAlign = TextAlign.Start,
                modifier = Modifier.height(MaterialTheme.dimens.large2),
                placeholderText = "Enter email address here"
            )
            // password
            Text(
                modifier = Modifier.padding(bottom = 10.dp, top = 25.dp),
                text = "Password",
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.onPrimary
            )
            CustomTextField(
                textAlign = TextAlign.Start,
                placeholderText = "Enter your password here",
                modifier = Modifier.height(MaterialTheme.dimens.large2)
            )
            // forgot password
            Text(
                modifier = Modifier
                    .clickable(onClick = { navHostController.navigate(NavigationScreenNames.ForgotPassword.route) })
                    .padding(
                        start = MaterialTheme.dimens.small1,
                        bottom = MaterialTheme.dimens.small1,
                        top = MaterialTheme.dimens.medium1),
                text = "Forgot Password ?",
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.onPrimary
            )

        }
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            // login button
            CustomButton(
                modifier = Modifier
                    .height(MaterialTheme.dimens.mediumButtonHeight)
                    .width(MaterialTheme.dimens.mediumButtonWidth),
                text = "Log in",
                onClick = {
                    navHostController.navigate(NavigationScreenNames.MainScreens.route){
                    popUpTo(route = NavigationScreenNames.Login.route){
                        inclusive = true
                    }
                }},
                style = MaterialTheme.typography.titleLarge
            )

            // facebook, google and sign in section
            Column(
                modifier = Modifier.fillMaxHeight(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top)
            {
                // facebook button
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
                            top = MaterialTheme.dimens.medium3)
                        .width(MaterialTheme.dimens.largeButtonWidth)
                        .height(MaterialTheme.dimens.largeButtonHeight),
                    text = "Log in with Facebook",
                    onClick = { },
                    backgroundColor = Facebook,
                    textColor = Color.White,
                    style = MaterialTheme.typography.titleSmall
                )

                // google button
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
                    text = "Log in with Google",
                    onClick = {},
                    backgroundColor = Color.White,
                    textColor = Color.Black,
                    style = MaterialTheme.typography.titleSmall
                )

                // Switch to Sign in
                Text(
                    modifier = Modifier.padding(
                        bottom = 15.dp,
                        top = 30.dp
                    ),
                    text = "Don't have an account ?",
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.onPrimary
                )
                Text(
                    modifier = Modifier
                        .clickable(onClick = { navHostController.navigate(NavigationScreenNames.SignUp.route) })
                        .padding(bottom = 10.dp,),
                    text = "Sign in",
                    style = MaterialTheme.typography.headlineSmall,
                    color = MaterialTheme.colorScheme.primary
                )
            }
        }
    }
}



@Preview
@Composable
private fun LoginPreview(){
    HeavyDutyTheme(dynamicColor = false) {
        Login(navHostController = rememberNavController())
    }
}