package com.kurz.password_generator.service

import com.kurz.password_generator.model.PasswordRequest
import org.springframework.stereotype.Service
import java.security.SecureRandom

@Service
class PasswordGeneratorService {
    private val random = SecureRandom()

    private val upperCaseChars = ('A'..'Z').toList()
    private val lowerCaseChars = ('a'..'z').toList()
    private val numberChars = ('0'..'9').toList()
    private val specialChars = "!@#$%^&*()-_=+[]{}|;:,.<>?".toList()

    fun generatePassword(request: PasswordRequest): String {
        val enabledClasses = mutableListOf<List<Char>>()

        if (request.useUppercase) enabledClasses.add(upperCaseChars)
        if (request.useLowercase) enabledClasses.add(lowerCaseChars)
        if (request.useNumber) enabledClasses.add(numberChars)
        if (request.useSpecialChar) enabledClasses.add(specialChars)

        // At least one character must be enabled
        if (enabledClasses.isEmpty()) {
            throw IllegalArgumentException("At least one character class must be enabled")
        }

        val pool = enabledClasses.flatten()

        // At least one character from each enabled class should be guaranteed
        val password = enabledClasses.map{ it[random.nextInt(it.size)] }.toMutableList()

        // Fill the rest randomly
        repeat(request.length - password.size) {
            password.add(pool[random.nextInt(pool.size)])
        }

        // Then shuffle
        password.shuffle(random)

        return password.joinToString("")
    }
}