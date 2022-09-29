package com.oguzhanturkmen.movee.presentation.personDetail

import com.oguzhanturkmen.movee.domain.model.person.Person

data class PersonDetailState(
    val isLoading: Boolean = false,
    val person: Person? = null,
    val error: String = ""
)