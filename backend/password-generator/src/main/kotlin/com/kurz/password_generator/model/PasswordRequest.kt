package com.kurz.password_generator.model

import jakarta.validation.constraints.Max
import jakarta.validation.constraints.Min


data class PasswordRequest(
    @field:Min(6) @field:Max(99)
    val length: Int = 12,
    val useUppercase: Boolean = true,
    val useLowercase: Boolean = true,
    val useNumber : Boolean = true,
    val useSpecialChar : Boolean = false
)