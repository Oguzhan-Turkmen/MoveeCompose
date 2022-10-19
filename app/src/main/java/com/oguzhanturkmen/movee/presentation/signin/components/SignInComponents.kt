package com.oguzhanturkmen.movee.presentation.signin.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.oguzhanturkmen.movee.R
import com.oguzhanturkmen.movee.ui.theme.RatingBarColor

@Composable
fun passwordSignInTextField() {
    var passwordInput by rememberSaveable { mutableStateOf("") }
    val focusRequester = remember { FocusRequester() }
    var passwordVisibility by remember { mutableStateOf(false) }

    Column(
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Password",
            color = Color.White
        )
        val icon = if (passwordVisibility)
            painterResource(id = R.drawable.password_visibility)
        else
            painterResource(id = R.drawable.password_visibility_off)
        TextField(
            value = passwordInput,
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Color.White,
                unfocusedBorderColor = Color.White,
                disabledLabelColor = Color.White,
                textColor = Color.White
            ),
            visualTransformation = if (passwordVisibility) VisualTransformation.None
            else PasswordVisualTransformation(),
            onValueChange = {
                passwordInput = it
            },
            modifier = Modifier
                .size(300.dp, 50.dp)
                .focusRequester(focusRequester = focusRequester),
            singleLine = true,
            trailingIcon = {
                IconButton(onClick = {
                    passwordVisibility = !passwordVisibility
                }) {
                    Icon(
                        tint = Color.White,
                        painter = icon,
                        contentDescription = "visibility_icon",
                        modifier = Modifier.size(24.dp)
                    )
                }
            },
            placeholder = {
                Text(
                    color = Color.White,
                    text = "Enter Password",
                )
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password,
            ),
        )
    }
}

@Composable
fun eMailSignInTextField() {
    var emailInput: String by remember { mutableStateOf("") }
    val focusRequester = remember { FocusRequester() }

    Column(

        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Email",
            color = Color.White
        )
        TextField(
            value = emailInput,
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Color.White,
                unfocusedBorderColor = Color.White,
                disabledLabelColor = Color.White,
                textColor = Color.White
            ),
            //colors = TextFieldDefaults.textFieldColors(Color.White),
            onValueChange = {
                emailInput = it
            },
            modifier = Modifier
                .size(300.dp, 50.dp)
                .focusRequester(focusRequester = focusRequester),
            singleLine = true,
            placeholder = {
                Text(
                    color = Color.White,
                    text = "Enter Email",
                )
            },
            keyboardOptions = KeyboardOptions(
                autoCorrect = true,
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Search
            ),

            )
    }
}

@Composable
fun nameSignInTextField() {
    var emailInput: String by remember { mutableStateOf("") }
    val focusRequester = remember { FocusRequester() }

    Column(

        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Name",
            color = Color.White
        )
        TextField(
            value = emailInput,
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Color.White,
                unfocusedBorderColor = Color.White,
                disabledLabelColor = Color.White,
                textColor = Color.White
            ),
            //colors = TextFieldDefaults.textFieldColors(Color.White),
            onValueChange = {
                emailInput = it
            },
            modifier = Modifier
                .size(300.dp, 50.dp)
                .focusRequester(focusRequester = focusRequester),
            singleLine = true,
            placeholder = {
                Text(
                    color = Color.White,
                    text = "Enter Name",
                )
            },
            keyboardOptions = KeyboardOptions(
                autoCorrect = true,
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Search
            ),

            )
    }
}

@Composable
fun signInButton() {
    Button(
        onClick = {},
        modifier = Modifier
            .size(300.dp, 40.dp),
        colors = ButtonDefaults.buttonColors(backgroundColor = Color.White)
    ) {
        Text(
            style = TextStyle(fontSize = 17.sp),
            fontWeight = FontWeight.Bold,
            text = "Sign In",
            color = RatingBarColor
        )
    }
}