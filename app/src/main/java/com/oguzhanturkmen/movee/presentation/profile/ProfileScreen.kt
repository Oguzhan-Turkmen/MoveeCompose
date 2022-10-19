package com.oguzhanturkmen.movee.presentation.profile

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.oguzhanturkmen.movee.common.gradient
import com.oguzhanturkmen.movee.presentation.navigation.MainScreens
import com.oguzhanturkmen.movee.presentation.signin.FirebaseViewModel

@Composable
fun ProfileScreen(
    navController: NavController,
    firebaseViewModel: FirebaseViewModel = hiltViewModel()
) {
    Box(modifier = Modifier
        .fillMaxSize()
        .drawBehind {
            drawRect(
                brush = gradient,
                topLeft = Offset(x = 0f, y = 0.dp.toPx()),
                size = Size(500.dp.toPx(), 250.dp.toPx())
            )
        }) {
        Column(modifier = Modifier.padding(top = 48.dp, start = 32.dp)) {
            Text(
                style = TextStyle(fontSize = 40.sp),
                color = Color.White,
                fontWeight = FontWeight.Bold,
                text = "Profile"
            )
            Text(
                style = TextStyle(fontSize = 34.sp),
                color = Color.White,
                text = "Hello \n " + firebaseViewModel.currentUser?.displayName ?: ""
            )

            Button(
                onClick = {
                    firebaseViewModel?.logout()
                    navController.navigate(MainScreens.MainLoginScreen.route) {
                        popUpTo("main_profile_screen") { inclusive = true }
                    }
                },
                modifier = Modifier
                    .padding(top = 4.dp)
            ) {
                Text(text = "Log Out")
            }
        }
    }
}