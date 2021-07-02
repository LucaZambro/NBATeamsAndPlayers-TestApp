package com.example.nbateamsandplayers_testapp.framework.network


import com.example.app_domain.model.Team
import com.google.gson.annotations.SerializedName

data class TeamDataModel(
    @SerializedName("data")
    val `data`: List<Data>,
    @SerializedName("meta")
    val meta: Meta
) {
    data class Data(
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

    data class Meta(
        @SerializedName("current_page")
        val currentPage: Int,
        @SerializedName("next_page")
        val nextPage: Any,
        @SerializedName("per_page")
        val perPage: Int,
        @SerializedName("total_count")
        val totalCount: Int,
        @SerializedName("total_pages")
        val totalPages: Int
    )
}

fun TeamDataModel.toDomainModel(): List<Team> {
    val teams = mutableListOf<Team>()
    this.data.forEach {
        teams.add(
            Team(
                id = it.id,
                abbreviation = it.abbreviation,
                city = it.city,
                conference = it.conference,
                division = it.division,
                fullName = it.fullName,
                name = it.name
            )
        )
    }
    return teams
}