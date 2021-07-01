package com.example.app_data.repository

import com.example.app_data.network.NetworkDatasource
import com.example.app_domain.model.Player
import com.example.app_domain.repository.PlayerRepository

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PlayerRepositoryImpl @Inject constructor(
    private val networkDatasource: NetworkDatasource,
) : PlayerRepository {

    override suspend fun getPlayers(page: Int): Map<Int, List<Player>> = networkDatasource.getPlayers(page)

    override suspend fun getPlayer(id: Int): Player = networkDatasource.getPlayer(id)

    override suspend fun getFilteredPlayers(filter: String): List<Player> = networkDatasource.getFilteredPlayers(filter)

}