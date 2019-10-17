package com.lynas.springsecurityjwt.dto

data class AuthenticationRequest(val username: String, val password: String)

data class SuccessResponse(val success: Boolean)