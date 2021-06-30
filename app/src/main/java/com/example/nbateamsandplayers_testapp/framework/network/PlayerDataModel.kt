package com.example.nbateamsandplayers_testapp.framework.network


import com.example.app_domain.model.Player
import com.example.app_domain.model.Team
import com.google.gson.annotations.SerializedName

data class PlayerDataModel(
    @SerializedName("data")
    val `data`: List<Data>,
    @SerializedName("meta")
    val meta: Meta
) {
    data class Data(
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

    data class Meta(
        @SerializedName("current_page")
        val currentPage: Int,
        @SerializedName("next_page")
        val nextPage: Int,
        @SerializedName("per_page")
        val perPage: Int,
        @SerializedName("total_count")
        val totalCount: Int,
        @SerializedName("total_pages")
        val totalPages: Int
    )
}

fun PlayerDataModel.toDomainModel(): List<Player> {
    val players = mutableListOf<Player>()
    this.data.forEach {
        players.add(
            Player(
                id = it.id ?: 0,
                firstName = it.firstName.orEmpty(),
                lastName = it.lastName.orEmpty(),
                position = it.position.orEmpty(),
                team = it.team.toDomainModel(),
                height = (it.heightFeet ?: 0.0) as Double,
                weight = (it.weightPounds ?: 0.0) as Double
            )
        )
    }
    return players
}

fun PlayerDataModel.Data.Team.toDomainModel(): Team = Team(
    id = this.id ?: 0,
    abbreviation = this.abbreviation.orEmpty(),
    city = this.city.orEmpty(),
    conference = this.conference.orEmpty(),
    division = this.division.orEmpty(),
    fullName = this.fullName.orEmpty(),
    name = this.name.orEmpty()
)

