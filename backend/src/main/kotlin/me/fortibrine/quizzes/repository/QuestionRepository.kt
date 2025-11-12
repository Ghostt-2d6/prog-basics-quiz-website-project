package me.fortibrine.quizzes.repository

import me.fortibrine.quizzes.model.Question
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface QuestionRepository: JpaRepository<Question, Long> {
}