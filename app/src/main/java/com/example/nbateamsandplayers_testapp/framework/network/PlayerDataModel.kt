package com.example.nbateamsandplayers_testapp.framework.network

import com.example.app_domain.model.Player
import com.example.app_domain.model.Team
import com.google.gson.annotations.SerializedName

data class PlayerDataModel (
    @SerializedName("id")
    val id: Int,
    @SerializedName("first_name")
    val firstName: String,
    @SerializedName("last_name")
    val lastName: String,
    @SerializedName("position")
    val position: String,
    @SerializedName("team")
    val team: TeamDataModel,
    @SerializedName("height_inches")
    val height: Double,
    @SerializedName("weight_pounds")
    val weight: Double
)

fun PlayerDataModel.toDomainModel() = Player(
    id = this.id ?: 0,
    firstName = this.firstName.orEmpty(),
    lastName = this.lastName.orEmpty(),
    position = this.position.orEmpty(),
    team = this.team.toDomainModel(),
    height = this.height ?: 0.0,
    weight = this.weight ?: 0.0,
    )

fun List<PlayerDataModel>.toDomainModel() = this.map { it.toDomainModel() }

