package com.lynas.springsecurityjwt.filter

import com.lynas.springsecurityjwt.AppConstant
import com.lynas.springsecurityjwt.TokenUtils
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource
import org.springframework.web.context.support.WebApplicationContextUtils
import java.io.IOException
import javax.servlet.FilterChain
import javax.servlet.ServletException
import javax.servlet.ServletRequest
import javax.servlet.ServletResponse
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse


class AuthenticationTokenFilter : UsernamePasswordAuthenticationFilter() {

    lateinit var userDetailsService: UserDetailsService
    lateinit var tokenUtils: TokenUtils

    @Throws(IOException::class, ServletException::class)
    override fun doFilter(request: ServletRequest, response: ServletResponse, chain: FilterChain) {
        tokenUtils = WebApplicationContextUtils
                .getRequiredWebApplicationContext(this.servletContext)
                .getBean(TokenUtils::class.java)
        userDetailsService = WebApplicationContextUtils
                .getRequiredWebApplicationContext(this.servletContext)
                .getBean(UserDetailsService::class.java)

        val resp = response as HttpServletResponse
        resp.setHeader("Access-Control-Allow-Origin", "*")
        resp.setHeader("Access-Control-Allow-Methods", "POST, GET, PUT, OPTIONS, DELETE, PATCH")
        resp.setHeader("Access-Control-Max-Age", "3600")
        resp.setHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept, " + AppConstant.tokenHeader)

        val httpRequest = request as HttpServletRequest
        if (httpRequest.requestURI != "/auth") {
            val authToken = httpRequest.getHeader(AppConstant.tokenHeader)
            val username = this.tokenUtils.getUsernameFromToken(authToken)

            if (SecurityContextHolder.getContext().authentication == null) {
                val userDetails = this.userDetailsService.loadUserByUsername(username)
                if (this.tokenUtils.validateToken(authToken, userDetails)) {
                    val authentication = UsernamePasswordAuthenticationToken(userDetails, null, userDetails.authorities)
                    authentication.details = WebAuthenticationDetailsSource().buildDetails(httpRequest)
                    SecurityContextHolder.getContext().authentication = authentication
                }
            }
        }
        chain.doFilter(request, response)
    }

}