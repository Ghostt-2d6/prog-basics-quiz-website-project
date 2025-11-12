package me.fortibrine.quizzes.model

import jakarta.persistence.CascadeType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.OneToMany

class Quiz (
    @field:Id
    @field:GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long,

    var title: String = "",
    var description: String = "",

    @OneToMany(cascade = [(CascadeType.ALL)])
    var questions: MutableList<Question> = mutableListOf()
)