package com.example.app_domain.repository

import com.example.app_domain.model.Player

interface PlayerRepository {

    suspend fun getPlayers(page: Int): Map<Int, List<Player>>

    suspend fun getPlayer(id: Int): Player

    suspend fun getFilteredPlayers(filter: String, page: Int): Map<Int, List<Player>>

}