package com.lynas.springsecurityjwt.dto

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.AuthorityUtils
import org.springframework.security.core.userdetails.UserDetails

data class SpringSecurityUserDTO(
        val id: Long,
        val appUsername: String,
        val appUserPassword: String,
        val authorities: String
) : UserDetails {
    override fun getUsername(): String {
        return appUsername
    }

    override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
        return AuthorityUtils.commaSeparatedStringToAuthorityList(authorities)
    }

    // Bellow value should be received from DB
    // Default value given for demo purpose

    override fun isEnabled() = true

    override fun isCredentialsNonExpired() = true

    override fun getPassword() = appUserPassword

    override fun isAccountNonExpired() = true

    override fun isAccountNonLocked() = true

}