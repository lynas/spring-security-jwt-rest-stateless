package com.lynas.springsecurityjwt.service

import com.lynas.springsecurityjwt.dto.SpringSecurityUserDTO
import com.lynas.springsecurityjwt.repository.AppUserRepository
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

@Service("userDetailsService")
class UserDetailServiceImpl(val appUserRepository: AppUserRepository) : UserDetailsService {

    override fun loadUserByUsername(username: String): UserDetails {
        appUserRepository.findByUsername(username)?.let {
            return SpringSecurityUserDTO(
                    appUsername = it.username,
                    appUserPassword = it.password,
                    authorities = it.authorities
            )
        } ?: throw UsernameNotFoundException(String.format("No User found with username: $username"))
    }

}

