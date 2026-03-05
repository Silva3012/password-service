package com.kurz.password_generator.controller

import com.kurz.password_generator.model.PasswordRequest
import com.kurz.password_generator.service.PasswordGeneratorService
import org.junit.jupiter.api.Test
import org.mockito.ArgumentMatchers
import org.mockito.Mockito.`when`
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest
import org.springframework.test.context.bean.override.mockito.MockitoBean
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@WebMvcTest(PasswordController::class)
class PasswordControllerTest {

    @Autowired
    private lateinit var mockMvc: MockMvc

    @MockitoBean
    private lateinit var service: PasswordGeneratorService

    @Suppress("UNCHECKED_CAST")
    private fun <T> anyNonNull(): T = ArgumentMatchers.any<T>() as T

    @Test
    fun `generatePassword returns password of the specified length`() {
        val expectedPassword = "AbCdEf12!@#$%^&*"
        `when`(service.generatePassword(anyNonNull())).thenReturn(expectedPassword)

        mockMvc.perform(
            get("/api/generate")
                .param("length", "16")
                .param("useUpperCase", "true")
                .param("useLowerCase", "true")
                .param("useNumber", "true")
                .param("useSpecialChar", "true")
        )
            .andExpect(status().isOk)
            .andExpect(content().string(expectedPassword))
    }
}
