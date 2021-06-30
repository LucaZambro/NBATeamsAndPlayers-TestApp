package com.example.app_data.repository

import com.example.app_data.network.NetworkDatasource
import com.example.app_domain.model.Team
import com.example.app_domain.repository.TeamRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TeamRepositoryImpl @Inject constructor(
    private val networkDatasource: NetworkDatasource,
) : TeamRepository {

    override suspend fun getTeams(): List<Team> = networkDatasource.getTeams()

}