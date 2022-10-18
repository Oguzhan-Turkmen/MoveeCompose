package com.oguzhanturkmen.movee.presentation.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.oguzhanturkmen.movee.R
import com.oguzhanturkmen.movee.presentation.login.components.*

@Composable
fun loginScreen(
    navController: NavController
) {
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
                eMailTextField()
                Spacer(modifier = Modifier.height(40.dp))
                passwordTextField()
                forgotPasswordText(onClick = {})
                Spacer(modifier = Modifier.height(40.dp))
                loginButton()
            }
            accountText(onClick = {})
        }
    }
}

