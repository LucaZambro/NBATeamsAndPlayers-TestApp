package com.example.app_domain.model

data class Player (
    val id: Int = 0,
    val firstName: String = "",
    val lastName: String = "",
    val position: String = "",
    val team: Team = Team(),
    val height: Double = 0.0,
    val weight: Double = 0.0
)