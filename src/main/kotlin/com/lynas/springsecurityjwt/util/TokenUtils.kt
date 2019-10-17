package com.lynas.springsecurityjwt.util

import com.lynas.springsecurityjwt.dto.SpringSecurityUserDTO
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Component
import java.util.*


@Component
class TokenUtils {

    private val secret = "Secret" // should be taken from external source e.g. property file or env file

    private val expiration = 604800L

    fun getUsernameFromToken(token: String) = getClaimsFromToken(token).subject
            ?: throw RuntimeException("Unable to get username from token String")

    private fun getClaimsFromToken(token: String) = Jwts.parser()
            .setSigningKey(this.secret)
            .parseClaimsJws(token)
            .body ?: throw RuntimeException("Unable to parse claim from token string")

    fun validateToken(token: String, userDetails: UserDetails): Boolean {
        val user = userDetails as SpringSecurityUserDTO
        val username = this.getUsernameFromToken(token)
        return (username == user.username
                && !this.isTokenExpired(token))
    }


    fun getExpirationDateFromToken(token: String) = getClaimsFromToken(token).expiration
            ?: throw RuntimeException("Unable to parse expiration date from token string")

    fun isTokenExpired(token: String) = getExpirationDateFromToken(token).before(Date())


    fun generateToken(userDetails: UserDetails): String {
        val claims = HashMap<String, Any>()
        claims["sub"] = userDetails.username
        claims["audience"] = "web"
        claims["created"] = Date()
        return this.generateToken(claims)
    }

    private fun generateToken(claims: Map<String, Any>): String {
        return Jwts.builder()
                .setClaims(claims)
                .setExpiration(this.generateExpirationDate())
                .signWith(SignatureAlgorithm.HS512, this.secret)
                .compact()
    }

    private fun generateExpirationDate(): Date {
        return Date(System.currentTimeMillis() + this.expiration * 1000)
    }
}