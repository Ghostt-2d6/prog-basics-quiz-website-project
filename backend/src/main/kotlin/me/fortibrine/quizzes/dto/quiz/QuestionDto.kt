package me.fortibrine.quizzes.dto.quiz

data class QuestionDto (
    val id: Long,
    val description: String,
    val options: List<String>,
    val answer: Int
)