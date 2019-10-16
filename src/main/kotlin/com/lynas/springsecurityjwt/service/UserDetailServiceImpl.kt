package com.lynas.springsecurityjwt.service

import com.lynas.springsecurityjwt.repository.AppUserRepository
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.AuthorityUtils
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service
import java.lang.RuntimeException

@Service("userDetailsService")
class UserDetailServiceImpl(val appUserRepository: AppUserRepository) : UserDetailsService {
    override fun loadUserByUsername(username: String): UserDetails {
        appUserRepository.findByUsername(username)?.let {
            return SpringSecurityUser(
                    id= it.id ?: throw RuntimeException("ID in table must not be null"),
                    appUsername = it.username,
                    appUserPassword = it.password,
                    authorities = it.authorities
            )
        } ?: throw UsernameNotFoundException(String.format("No User found with username: $username"))
    }
}

data class SpringSecurityUser(
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