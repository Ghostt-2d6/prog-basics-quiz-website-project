package me.fortibrine.quizzes.controller

import jakarta.validation.Valid
import me.fortibrine.quizzes.dto.login.*
import me.fortibrine.quizzes.dto.register.RegisterRequestDto
import me.fortibrine.quizzes.dto.register.RegisterValidator
import me.fortibrine.quizzes.exception.ValidationError
import me.fortibrine.quizzes.model.User
import me.fortibrine.quizzes.repository.UserRepository
import me.fortibrine.quizzes.service.HashService
import me.fortibrine.quizzes.service.TokenService
import me.fortibrine.quizzes.service.UserService
import org.springframework.security.oauth2.server.resource.InvalidBearerTokenException
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/auth")
class AuthController(
    private val hashService: HashService,
    private val tokenService: TokenService,
    private val userService: UserService,
    private val loginValidator: LoginValidator,
    private val registerValidator: RegisterValidator,
    private val userRepository: UserRepository
) {

    @PostMapping("/login")
    fun login(
        @Valid
        @RequestBody
        payload: LoginRequestDto,

        bindingResult: BindingResult
    ): LoginResponseDto {

        loginValidator.validate(payload, bindingResult)

        if (bindingResult.hasErrors()) {
            throw ValidationError(bindingResult.fieldErrors.associate {
                it.field to it.defaultMessage.orEmpty()
            })
        }

        val user = userService.findByUsername(payload.username) as User
        val refreshToken = tokenService.createRefreshToken(user)

        user.tokens.add(refreshToken)

        return LoginResponseDto(
            accessToken = tokenService.createAccessToken(user),
            refreshToken = refreshToken
        )
    }

    @PostMapping("/register")
    fun register(
        @Valid
        @RequestBody
        payload: RegisterRequestDto,

        bindingResult: BindingResult
    ): LoginResponseDto {

        registerValidator.validate(payload, bindingResult)

        if (bindingResult.hasErrors()) {
            throw ValidationError(bindingResult.fieldErrors.associate {
                it.field to it.defaultMessage.orEmpty()
            })
        }

        val user = userService.save(User(
            username = payload.username,
            password = hashService.hashBcrypt(payload.password),
        ))

        val refreshToken = tokenService.createRefreshToken(user)

        user.tokens.add(refreshToken)

        return LoginResponseDto(
            accessToken = tokenService.createAccessToken(user),
            refreshToken = refreshToken
        )
    }

    @PostMapping("/refresh")
    fun refresh(
        @RequestBody
        payload: RefreshRequestDto,
    ): RefreshResponseDto {
        val user = tokenService.parseRefreshToken(payload.refreshToken)
            ?: throw InvalidBearerTokenException("Invalid token")

        if (!user.tokens.contains(payload.refreshToken))
            throw InvalidBearerTokenException("Invalid token")

        return RefreshResponseDto(
            accessToken = tokenService.createAccessToken(user),
        )
    }

    @PostMapping("/logout")
    fun logout(
        @RequestBody
        payload: RefreshRequestDto,
    ) {
        val user = tokenService.parseRefreshToken(payload.refreshToken)
            ?: throw InvalidBearerTokenException("Invalid token")

        if (!user.tokens.contains(payload.refreshToken))
            throw InvalidBearerTokenException("Invalid token")

        user.tokens.remove(payload.refreshToken)
    }

    @PostMapping("/logout/all")
    fun logoutEverywhere(
        @RequestBody
        payload: RefreshRequestDto
    ) {
        val user = tokenService.parseRefreshToken(payload.refreshToken)
            ?: throw InvalidBearerTokenException("Invalid token")

        user.tokens = mutableSetOf(payload.refreshToken)
        userRepository.save(user)
    }

}