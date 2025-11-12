package me.fortibrine.quizzes.mapper

import me.fortibrine.quizzes.dto.user.UserDto
import me.fortibrine.quizzes.model.User
import org.mapstruct.Mapper
import org.mapstruct.Mapping

@Mapper(componentModel = "spring")
interface UserMapper {
    @Mapping(source = "user.username", target = "username")
    fun toDto(user: User): UserDto
}