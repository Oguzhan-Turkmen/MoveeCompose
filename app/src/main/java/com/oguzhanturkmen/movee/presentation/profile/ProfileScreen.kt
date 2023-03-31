package com.oguzhanturkmen.movee.presentation.profile

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.scale
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.oguzhanturkmen.movee.common.gradient
import com.oguzhanturkmen.movee.presentation.navigation.MainScreens
import com.oguzhanturkmen.movee.presentation.popularMovie.components.PopularMoviesItem
import com.oguzhanturkmen.movee.presentation.signin.FirebaseViewModel

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ProfileScreen(
    navController: NavController,
    firebaseViewModel: FirebaseViewModel = hiltViewModel(),
    profileScreenViewModel: ProfileScreenViewModel = hiltViewModel()
) {
    val state = profileScreenViewModel.state.value
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
                text = ("Hello \n " + firebaseViewModel.currentUser?.displayName)
            )

            Button(
                onClick = {
                    firebaseViewModel.logout()
                    navController.navigate(MainScreens.MainLoginScreen.route) {
                        popUpTo("main_profile_screen") { inclusive = true }
                    }
                },
                modifier = Modifier
                    .padding(top = 4.dp)
            ) {
                Text(text = "Log Out")
            }
            Spacer(modifier = Modifier.height(15.dp))
            Text(
                style = TextStyle(fontSize = 34.sp),
                color = Color.Black,
                text = ("Saved Movies")
            )
            Spacer(modifier = Modifier.height(4.dp))

            LazyColumn(
                modifier = Modifier.padding(end = 32.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
            ) {
                items(state.savedMovies) { movie ->
                    val dismissState = rememberDismissState(
                        confirmStateChange = {
                            if (it == DismissValue.DismissedToEnd || it == DismissValue.DismissedToStart) {
                                profileScreenViewModel.deleteMovie(movie)
                            }
                            true
                        }
                    )
                    SwipeToDismiss(
                        state = dismissState,
                        directions = setOf(
                            DismissDirection.StartToEnd,
                            DismissDirection.EndToStart
                        ),
                        dismissThresholds = { FractionalThreshold(0.2f) },
                        background = {
                            val diretion = dismissState.dismissDirection ?: return@SwipeToDismiss
                            val color by animateColorAsState(
                                targetValue = when (dismissState.targetValue) {
                                    DismissValue.Default -> Color.LightGray
                                    DismissValue.DismissedToEnd -> Color.Red
                                    DismissValue.DismissedToStart -> Color.Red
                                }
                            )
                            val icon = when (diretion) {
                                DismissDirection.StartToEnd -> Icons.Default.Delete
                                DismissDirection.EndToStart -> Icons.Default.Delete
                            }

                            val scale by animateFloatAsState(targetValue = if (dismissState.targetValue == DismissValue.Default) 0.8f else 1.2f)

                            val alignment = when (diretion) {
                                DismissDirection.EndToStart -> Alignment.CenterEnd
                                DismissDirection.StartToEnd -> Alignment.CenterStart
                            }
                            Box(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .background(color)
                                    .padding(start = 12.dp, end = 12.dp),
                                contentAlignment = alignment
                            ) {
                                Icon(
                                    icon,
                                    contentDescription = "Icon",
                                    modifier = Modifier.scale(scale)
                                )
                            }
                        },
                        dismissContent = {
                            Card(
                                modifier = Modifier.fillMaxWidth(),
                                elevation = animateDpAsState(targetValue = if (dismissState.dismissDirection != null) 4.dp else 0.dp).value
                            ) {
                                PopularMoviesItem(
                                    movie = movie,
                                    modifier = Modifier,
                                    onClick = {
                                        navController.navigate(MainScreens.MainMovieDetailScreen.route + "${movie.id}")
                                    }
                                )
                            }
                        }
                    )

                }
            }
            if (state.error.isNotBlank()) {
                Text(
                    text = state.error,
                    color = MaterialTheme.colors.error,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp)
                )
            }

        }

    }
}

