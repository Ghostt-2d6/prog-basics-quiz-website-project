package me.fortibrine.quizzes.exception

class ValidationError (
    val errors: Map<String, String>
) : RuntimeException("Validation failed")
