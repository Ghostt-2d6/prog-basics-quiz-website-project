package me.fortibrine.quizzes.model

import jakarta.persistence.CascadeType
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.OneToMany

@Entity
class Quiz (
    @field:Id
    @field:GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long? = null,

    var title: String,
    var description: String,

    @OneToMany(cascade = [(CascadeType.ALL)])
    var questions: MutableList<Question>
)