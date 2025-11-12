package me.fortibrine.quizzes.service

import me.fortibrine.quizzes.model.User
import me.fortibrine.quizzes.repository.UserRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class UserService (
    private val userRepository: UserRepository
) {

    fun findById(id: Long): User? = userRepository.findByIdOrNull(id)
    fun findByUsername(username: String): User? = userRepository.findByUsername(username)
    fun existsByUsername(name: String): Boolean = userRepository.existsByUsername(name)
    fun save(user: User): User = userRepository.save(user)

}