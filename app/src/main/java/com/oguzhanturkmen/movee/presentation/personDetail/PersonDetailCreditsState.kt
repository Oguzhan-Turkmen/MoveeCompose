package com.oguzhanturkmen.movee.presentation.personDetail

import com.oguzhanturkmen.movee.domain.model.personcredits.PersonCredits


data class PersonDetailCreditsState(
    val isLoading: Boolean = false,
    val personCredit: List<PersonCredits> = emptyList(),
    val error: String = ""
)
