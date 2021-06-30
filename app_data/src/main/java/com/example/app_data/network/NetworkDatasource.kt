package com.example.app_data.network

import com.example.app_domain.model.Player
import com.example.app_domain.model.Team

interface NetworkDatasource {

    suspend fun getTeams(): List<Team>

    suspend fun getPlayers(): List<Player>

    suspend fun getPlayer(id: Int): List<Player>
}