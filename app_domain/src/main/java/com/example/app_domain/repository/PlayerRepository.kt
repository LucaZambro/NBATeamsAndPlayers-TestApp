package com.example.app_domain.repository

import com.example.app_domain.model.Player

interface PlayerRepository {

    suspend fun getPlayers(): List<Player>

    suspend fun getPlayer(id: Int): Player

}