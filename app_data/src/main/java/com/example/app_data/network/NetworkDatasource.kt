package com.example.app_data.network

import com.example.app_domain.model.Player
import com.example.app_domain.model.Team

interface NetworkDatasource {

    suspend fun getTeams(): List<Team>

    suspend fun getPlayers(page: Int): Map<Int, List<Player>>

    suspend fun getPlayer(id: Int): Player

    suspend fun getFilteredPlayers(filter: String, page: Int): Map<Int, List<Player>>

}