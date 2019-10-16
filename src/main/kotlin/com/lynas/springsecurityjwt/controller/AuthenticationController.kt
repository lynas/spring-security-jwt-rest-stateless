package com.lynas.springsecurityjwt.controller

import com.lynas.springsecurityjwt.util.TokenUtils
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import com.lynas.springsecurityjwt.dto.AuthenticationRequest
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMethod



@RestController
@RequestMapping("/auth")
class AuthenticationController(val authenticationManager: AuthenticationManager,
                               val  tokenUtils: TokenUtils,
                               val  userDetailsService: UserDetailsService) {
    @RequestMapping(method = [RequestMethod.POST])
    fun authenticationRequest(@RequestBody authenticationRequest: AuthenticationRequest): String {

        // Perform the authentication
        val authentication = this.authenticationManager.authenticate(
                UsernamePasswordAuthenticationToken(
                        authenticationRequest.username,
                        authenticationRequest.password
                )
        )
        SecurityContextHolder.getContext().authentication = authentication

        // Reload password post-authentication so we can generate token
        val userDetails = this.userDetailsService.loadUserByUsername(authenticationRequest.username)
        return this.tokenUtils.generateToken(userDetails)
    }
}