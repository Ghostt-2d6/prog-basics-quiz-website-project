package me.fortibrine.quizzes.dto.quiz

data class QuizDto (
    val id: Long,
    val title: String,
    val description: String,
    val questions: List<QuestionDto>
)