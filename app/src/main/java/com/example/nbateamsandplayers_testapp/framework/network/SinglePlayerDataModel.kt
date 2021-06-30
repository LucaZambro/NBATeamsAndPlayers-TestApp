package com.example.nbateamsandplayers_testapp.framework.network


import com.example.app_domain.model.Player
import com.example.app_domain.model.Team
import com.google.gson.annotations.SerializedName

data class SinglePlayerDataModel(
    @SerializedName("first_name")
    val firstName: String,
    @SerializedName("height_feet")
    val heightFeet: Any,
    @SerializedName("height_inches")
    val heightInches: Any,
    @SerializedName("id")
    val id: Int,
    @SerializedName("last_name")
    val lastName: String,
    @SerializedName("position")
    val position: String,
    @SerializedName("team")
    val team: Team,
    @SerializedName("weight_pounds")
    val weightPounds: Any
) {
    data class Team(
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
        @SerializedName("id")
        val id: Int,
        @SerializedName("name")
        val name: String
    )
}

fun SinglePlayerDataModel.toDomainModel() =  Player(
    id = this.id ?: 0,
    firstName = this.firstName.orEmpty(),
    lastName = this.lastName.orEmpty(),
    position = this.position.orEmpty(),
    team = this.team.toDomainModel(),
    height = (this.heightFeet ?: 0.0) as Double,
    weight = (this.weightPounds ?: 0.0) as Double
)

fun SinglePlayerDataModel.Team.toDomainModel(): Team = Team(
    id = this.id ?: 0,
    abbreviation = this.abbreviation.orEmpty(),
    city = this.city.orEmpty(),
    conference = this.conference.orEmpty(),
    division = this.division.orEmpty(),
    fullName = this.fullName.orEmpty(),
    name = this.name.orEmpty()
)