package com.lynas.springsecurityjwt.repository

import com.lynas.springsecurityjwt.model.AppUser
import org.springframework.data.jpa.repository.JpaRepository

interface AppUserRepository : JpaRepository<AppUser, Long> {
    fun findByUsername(username: String) : AppUser?

}