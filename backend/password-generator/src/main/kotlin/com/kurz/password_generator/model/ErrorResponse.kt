package com.kurz.password_generator.model

// For all 400 responses, to keep the shape consistent
data class ErrorResponse(
    val error: String,
    val details: String
)