package me.fortibrine.quizzes.repository

import me.fortibrine.quizzes.model.Quiz
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface QuizRepository: JpaRepository<Quiz, Long> {

}