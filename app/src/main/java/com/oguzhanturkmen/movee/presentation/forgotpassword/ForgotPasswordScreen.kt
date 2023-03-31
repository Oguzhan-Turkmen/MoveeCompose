package com.oguzhanturkmen.movee.presentation.forgotpassword

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.oguzhanturkmen.movee.R
import com.oguzhanturkmen.movee.presentation.theme.RatingBarColor

@Composable
fun ForgotPasswordScreen() {
    var emailInput: String by remember { mutableStateOf("") }
    val focusRequester = remember { FocusRequester() }

    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            modifier = Modifier.fillMaxSize(),
            painter = painterResource(R.drawable.user_login_signin_background),
            contentDescription = null,
            contentScale = ContentScale.FillBounds
        )
        Column(
            modifier = Modifier.padding(start = 40.dp, top = 100.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(80.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_login_icon),
                contentDescription = null,
                tint = Color.White
            )
            Column {
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
                    Spacer(modifier = Modifier.height(40.dp))
                    Button(
                        onClick = {},
                        modifier = Modifier
                            .size(300.dp, 40.dp),
                        colors = ButtonDefaults.buttonColors(backgroundColor = Color.White)
                    ) {
                        Text(
                            style = TextStyle(fontSize = 17.sp),
                            fontWeight = FontWeight.Bold,
                            text = "Reset Password",
                            color = RatingBarColor
                        )
                    }
                }
            }
        }
    }
}
