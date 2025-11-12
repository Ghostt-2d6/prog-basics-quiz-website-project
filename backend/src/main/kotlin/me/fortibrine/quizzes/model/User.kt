package me.fortibrine.quizzes.model

import jakarta.persistence.*

@Entity(name = "AppUser")
class User (
    @field:Id
    @field:GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long? = null,
    var username: String,
    var password: String,

    @field:ElementCollection(fetch = FetchType.EAGER)
    var roles: MutableSet<String> = mutableSetOf("ROLE_USER"),

    @field:ElementCollection(fetch = FetchType.EAGER)
    var tokens: MutableSet<String> = mutableSetOf()

)
