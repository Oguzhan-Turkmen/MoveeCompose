package com.oguzhanturkmen.movee.presentation.login

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.oguzhanturkmen.movee.R
import com.oguzhanturkmen.movee.common.Resource
import com.oguzhanturkmen.movee.presentation.login.components.accountText
import com.oguzhanturkmen.movee.presentation.login.components.forgotPasswordText
import com.oguzhanturkmen.movee.presentation.navigation.MainScreens
import com.oguzhanturkmen.movee.presentation.signin.FirebaseViewModel
import com.oguzhanturkmen.movee.presentation.theme.RatingBarColor

@Composable
fun loginScreen(
    navController: NavController,
    firebaseViewModel: FirebaseViewModel = hiltViewModel()
) {
    var emailInput: String by remember { mutableStateOf("") }
    val focusRequester = remember { FocusRequester() }
    var passwordInput by rememberSaveable { mutableStateOf("") }
    var passwordVisibility by remember { mutableStateOf(false) }
    val loginFlow = firebaseViewModel.loginFlow.collectAsState()
    val isError by rememberSaveable { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
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
                        isError = isError,
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
                Spacer(modifier = Modifier.height(40.dp))
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
                forgotPasswordText(onClick = {
                    navController.navigate(MainScreens.MainForgotPasswordScreen.route)
                })
                Spacer(modifier = Modifier.height(40.dp))
                Button(
                    onClick = {
                        firebaseViewModel.login(emailInput, passwordInput)
                    },
                    modifier = Modifier
                        .size(300.dp, 40.dp),
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color.White)
                ) {
                    Text(
                        style = TextStyle(fontSize = 17.sp),
                        fontWeight = FontWeight.Bold,
                        text = "Login",
                        color = RatingBarColor
                    )
                }
            }
            accountText(onClick = {
                navController.navigate(MainScreens.MainSignInScreen.route)
            })
            loginFlow.value?.let {
                when (it) {
                    is Resource.Loading -> {
                        CircularProgressIndicator(modifier = Modifier)
                    }
                    is Resource.Success -> {
                        LaunchedEffect(Unit) {
                            navController.navigate(MainScreens.MainMovieScreen.route) {
                                popUpTo("main_login_screen") { inclusive = true }
                            }
                        }
                    }
                    is Resource.Error -> {
                        val context = LocalContext.current
                        Toast.makeText(context, it.statusMessage, Toast.LENGTH_LONG).show()
                    }
                }
            }
        }
    }
}

