package com.example.app_domain.repository

import com.example.app_domain.model.Team

interface TeamRepository {

    suspend fun getTeams(): List<Team>

}