package me.fortibrine.quizzes.controller

import me.fortibrine.quizzes.dto.quiz.QuestionDto
import me.fortibrine.quizzes.dto.quiz.QuizDto
import me.fortibrine.quizzes.model.Question
import me.fortibrine.quizzes.model.Quiz
import me.fortibrine.quizzes.repository.QuestionRepository
import me.fortibrine.quizzes.repository.QuizRepository
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/quizzes")
class QuizzesController (
    private val quizRepository: QuizRepository,
    private val questionRepository: QuestionRepository
) {

    @GetMapping
    fun getQuizzes(): List<QuizDto> {
        val quizzes = quizRepository.findAll()
        val quizDtos = mutableListOf<QuizDto>()

        quizzes.forEach { quiz ->
            val questions = mutableListOf<QuestionDto>()
            quiz.questions.forEach { question -> questions.add(QuestionDto(
                id = question.id,
                description = question.description,
                options = question.options,
                answer = question.answer
            )) }

            quizDtos.add(QuizDto(
                id = quiz.id,
                title = quiz.title,
                description = quiz.description,
                questions = questions
            ))
        }

        return quizDtos
    }

    @GetMapping("/{id}")
    fun getQuiz(
        @PathVariable("id") id: Long
    ): QuizDto {
        val quiz = quizRepository.findById(id).orElseThrow()
        val questions = mutableListOf<QuestionDto>()

        quiz.questions.forEach { question -> questions.add(QuestionDto(
            id = question.id,
            description = question.description,
            options = question.options,
            answer = question.answer
        )) }

        return QuizDto(
            id = quiz.id,
            title = quiz.title,
            description = quiz.description,
            questions = questions
        )

    }

    @PostMapping
    fun createQuiz(
        @RequestBody quizDto: QuizDto
    ): ResponseEntity<Any> {
        val questions = quizDto.questions.map { question -> Question(
            description = question.description,
            options = question.options,
            answer = question.answer
        ) }

        val savedQuestions = questionRepository.saveAll(questions)
        val savedQuiz = quizRepository.save(Quiz(
            title = quizDto.title,
            description = quizDto.description,
            questions = savedQuestions.toMutableList()
        ))

        return ResponseEntity.noContent().build()
    }

}