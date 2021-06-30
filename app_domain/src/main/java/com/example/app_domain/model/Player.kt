package com.example.app_domain.model

data class Player (
    val id: Int = 0,
    val firstName: String = "",
    val lastName: String = "",
    val position: String = "",
    val team: Team,
    val height: Double,
    val weight: Double
)