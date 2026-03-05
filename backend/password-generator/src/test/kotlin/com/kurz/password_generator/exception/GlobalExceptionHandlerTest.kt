package com.kurz.password_generator.exception

import com.kurz.password_generator.controller.PasswordController
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
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@WebMvcTest(PasswordController::class)
class GlobalExceptionHandlerTest {

    @Autowired
    private lateinit var mockMvc: MockMvc

    @MockitoBean
    private lateinit var service: PasswordGeneratorService

    @Test
    fun `handles ConstraintViolationException for invalid request parameters`() {
        mockMvc.perform(
            get("/api/generate")
                .param("length", "3") // below @Min(6)
        )
            .andExpect(status().isBadRequest)
            .andExpect(jsonPath("$.error").value("Validation failed"))
            .andExpect(jsonPath("$.details").isString)
    }

    @Suppress("UNCHECKED_CAST")
    private fun <T> anyNonNull(): T = ArgumentMatchers.any<T>() as T

    @Test
    fun `handles IllegalArgumentException thrown by the service`() {
        `when`(service.generatePassword(anyNonNull()))
            .thenThrow(IllegalArgumentException("At least one character class must be enabled"))

        mockMvc.perform(
            get("/api/generate")
                .param("length", "12")
        )
            .andExpect(status().isBadRequest)
            .andExpect(jsonPath("$.error").value("Invalid request"))
            .andExpect(jsonPath("$.details").value("At least one character class must be enabled"))
    }
}
