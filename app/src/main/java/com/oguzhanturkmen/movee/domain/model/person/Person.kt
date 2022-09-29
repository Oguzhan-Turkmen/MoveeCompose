package com.oguzhanturkmen.movee.domain.model.person

data class Person(
    val id: Int,
    val name: String,
    val biography: String,
    val profilePath: String?
)
