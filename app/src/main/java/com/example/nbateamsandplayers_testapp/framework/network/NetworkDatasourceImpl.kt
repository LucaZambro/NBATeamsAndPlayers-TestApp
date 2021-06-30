package com.example.nbateamsandplayers_testapp.framework.network

import com.example.app_data.network.NetworkDatasource
import com.example.app_domain.model.Player
import com.example.app_domain.model.Team
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class NetworkDatasourceImpl @Inject constructor(
    private val apiInterface: APIInterface
) : NetworkDatasource {

    override suspend fun getTeams(): List<Team> {
        val response = apiInterface.getTeams()
        return if (response.isSuccessful) {
            response.body()?.toDomainModel().orEmpty()
        } else {
            arrayListOf()
        }
    }

    override suspend fun getPlayers(): List<Player> {
        val response = apiInterface.getPlayers(1)
        return if (response.isSuccessful) {
            response.body()?.toDomainModel().orEmpty()
        } else {
            arrayListOf()
        }
    }

    override suspend fun getPlayer(id: Int): List<Player> {
        val response = apiInterface.getPlayer(id)
        return if (response.isSuccessful) {
            response.body()?.toDomainModel().orEmpty()
        } else {
            arrayListOf()
        }
    }
}

