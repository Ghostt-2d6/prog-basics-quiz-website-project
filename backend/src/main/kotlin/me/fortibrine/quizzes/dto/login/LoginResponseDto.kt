package me.fortibrine.quizzes.dto.login

data class LoginResponseDto (
    val accessToken: String,
    val refreshToken: String,
)
