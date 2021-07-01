package com.example.nbateamsandplayers_testapp.framework.network

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface APIInterface {

    //DA SISTEMARE
    @GET("teams")
    suspend fun getTeams(): Response<TeamDataModel>

    @GET("players")
    suspend fun getPlayers(@Query("page") page: Int): Response<PlayerDataModel>

    @GET("players")
    suspend fun getFilteredPlayers(
        @Query("search") filter: String,
        @Query("page") page: Int
    ): Response<PlayerDataModel>

    @GET("players/{id}")
    suspend fun getPlayer(@Path("id") id: Int): Response<SinglePlayerDataModel>
}