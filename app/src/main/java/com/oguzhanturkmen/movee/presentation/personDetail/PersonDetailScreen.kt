package com.oguzhanturkmen.movee.presentation.personDetail

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.oguzhanturkmen.movee.presentation.navigation.MoviesScreen
import com.oguzhanturkmen.movee.presentation.personDetail.components.personDetailBiography
import com.oguzhanturkmen.movee.presentation.personDetail.components.personDetailImage
import com.oguzhanturkmen.movee.presentation.personDetail.components.personDetailName
import com.oguzhanturkmen.movee.presentation.personDetail.components.personelCreditsItem

@Composable
fun PersonDetailScreen(
    viewModel: PersonDetailViewModel = hiltViewModel(),
    navController: NavController
) {
    val personDetailState = viewModel.state.value
    val personalCreditState = viewModel.personcreditstate.value
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        personDetailState.person?.let { person ->
            LazyColumn(
                modifier = Modifier
                    .padding(start = 32.dp, top = 8.dp, end = 32.dp)
                    .fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(16.dp)

            ) {
                item {
                    personDetailImage(person = person)
                }
                item {
                    Column(
                        Modifier
                            .padding(start = 8.dp, top = 8.dp, end = 8.dp),
                        verticalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        personDetailName(person = person)
                        personDetailBiography(person = person)
                    }
                }
                items(personalCreditState.personCredit) { personalCredit ->
                    personelCreditsItem(
                        personCredits = personalCredit,
                        onClick = { navController.navigate(MoviesScreen.MovieDetailScreen.route + "${personalCredit.id}") }
                    )

                }
            }
        }

    }
}
