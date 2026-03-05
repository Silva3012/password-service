package com.kurz.password_generator.service

import com.kurz.password_generator.model.PasswordRequest
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class PasswordGeneratorServiceTest {

    private val service = PasswordGeneratorService()

    @Test
    fun `generatePassword with all character types includes each type`() {
        val request = PasswordRequest(
            length = 20,
            useUppercase = true,
            useLowercase = true,
            useNumber = true,
            useSpecialChar = true
        )

        val password = service.generatePassword(request)

        assertEquals(20, password.length)
        assertTrue(password.any { it.isUpperCase() }, "Password should contain uppercase letters")
        assertTrue(password.any { it.isLowerCase() }, "Password should contain lowercase letters")
        assertTrue(password.any { it.isDigit() }, "Password should contain digits")
        assertTrue(
            password.any { !it.isLetterOrDigit() },
            "Password should contain special characters"
        )
    }

    @Test
    fun `generatePassword throws IllegalArgumentException when no character classes enabled`() {
        val request = PasswordRequest(
            length = 12,
            useUppercase = false,
            useLowercase = false,
            useNumber = false,
            useSpecialChar = false
        )

        val exception = assertThrows<IllegalArgumentException> {
            service.generatePassword(request)
        }

        assertEquals("At least one character class must be enabled", exception.message)
    }
}
