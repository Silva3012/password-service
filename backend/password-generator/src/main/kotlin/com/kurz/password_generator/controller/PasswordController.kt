package com.kurz.password_generator.controller

import com.kurz.password_generator.model.PasswordRequest
import com.kurz.password_generator.service.PasswordGeneratorService
import jakarta.validation.Valid
import jakarta.validation.constraints.Max
import jakarta.validation.constraints.Min
import org.springframework.http.MediaType
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api")
@Validated
class PasswordController(private val service: PasswordGeneratorService) {

    @GetMapping("/generate", produces = [MediaType.TEXT_PLAIN_VALUE])
    fun generatePassword(
        @RequestParam(defaultValue = "12") @Min(6) @Max(99) length: Int,
        @RequestParam(defaultValue = "true") useUpperCase: Boolean,
        @RequestParam(defaultValue = "true") useLowerCase: Boolean,
        @RequestParam(defaultValue = "true") useNumber: Boolean,
        @RequestParam(defaultValue = "false") useSpecialChar: Boolean
    ): String {
        val request = PasswordRequest(length, useUpperCase, useLowerCase, useNumber, useSpecialChar)
        return service.generatePassword(request)
    }
}