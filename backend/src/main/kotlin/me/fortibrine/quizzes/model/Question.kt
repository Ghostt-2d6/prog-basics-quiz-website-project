package me.fortibrine.quizzes.model

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id

@Entity
class Question (

    @field:Id
    @field:GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long,

    var description: String = "",
    var options: List<String> = listOf(),
    var answer: Int = 0

)