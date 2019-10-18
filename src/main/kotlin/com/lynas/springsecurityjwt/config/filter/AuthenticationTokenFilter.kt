package com.lynas.springsecurityjwt.config.filter

import com.lynas.springsecurityjwt.util.TOKEN_HEADER
import com.lynas.springsecurityjwt.util.TokenUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource
import javax.servlet.FilterChain
import javax.servlet.ServletRequest
import javax.servlet.ServletResponse
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class AuthenticationTokenFilter : UsernamePasswordAuthenticationFilter() {

    @Autowired
    lateinit var tokenUtils: TokenUtils

    override fun doFilter(request: ServletRequest, response: ServletResponse, chain: FilterChain) {

        val resp = response as HttpServletResponse
        resp.setHeader("Access-Control-Allow-Origin", "*")
        resp.setHeader("Access-Control-Allow-Methods", "POST, GET, PUT, OPTIONS, DELETE, PATCH")
        resp.setHeader("Access-Control-Max-Age", "3600")
        resp.setHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept, $TOKEN_HEADER")

        val httpRequest = request as HttpServletRequest
        httpRequest.getHeader(TOKEN_HEADER)?.let {
            val authToken = httpRequest.getHeader(TOKEN_HEADER)
            try {
                tokenUtils.validateToken(authToken)
                val userDetails = tokenUtils.getUserDetailsFromToken(authToken)
                val authentication = UsernamePasswordAuthenticationToken(userDetails, null, userDetails.authorities)
                authentication.details = WebAuthenticationDetailsSource().buildDetails(httpRequest)
                SecurityContextHolder.getContext().authentication = authentication
            } catch (e: Exception) {
                e.printStackTrace()
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "The token is not valid.")
            }
        }
        chain.doFilter(request, response)
    }

}
