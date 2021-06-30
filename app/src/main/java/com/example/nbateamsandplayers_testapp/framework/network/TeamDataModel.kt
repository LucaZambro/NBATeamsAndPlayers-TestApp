package com.example.nbateamsandplayers_testapp.framework.network

import com.example.app_domain.model.Team
import com.google.gson.annotations.SerializedName

data class TeamDataModel(
    @SerializedName("id")
    val id: Int,
    @SerializedName("abbreviation")
    val abbreviation: String,
    @SerializedName("city")
    val city: String,
    @SerializedName("conference")
    val conference: String,
    @SerializedName("division")
    val division: String,
    @SerializedName("full_name")
    val fullName: String,
    @SerializedName("name")
    val name: String
)

fun TeamDataModel.toDomainModel() = Team(
    id = this.id ?: 0,
    abbreviation = this.abbreviation.orEmpty(),
    city = this.city.orEmpty(),
    conference = this.conference.orEmpty(),
    division = this.division.orEmpty(),
    fullName = this.fullName.orEmpty(),
    name = this.name.orEmpty()
)

fun List<TeamDataModel>.toDomainModel() = this.map { it.toDomainModel() }