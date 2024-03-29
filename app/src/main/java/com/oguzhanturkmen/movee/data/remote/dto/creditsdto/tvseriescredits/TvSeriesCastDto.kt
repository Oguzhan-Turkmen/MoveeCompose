package com.oguzhanturkmen.movee.data.remote.dto.creditsdto.tvseriescredits

import com.google.gson.annotations.SerializedName

data class TvSeriesCastDto(
    val adult: Boolean,
    val character: String,
    @SerializedName("credit_id")
    val creditId: String,
    val gender: Int,
    val id: Int,
    @SerializedName("known_for_department")
    val knownFoDepartment: String,
    val name: String,
    val order: Int,
    @SerializedName("original_name")
    val originalName: String,
    val popularity: Double,
    @SerializedName("profile_path")
    val profilePath: String
)