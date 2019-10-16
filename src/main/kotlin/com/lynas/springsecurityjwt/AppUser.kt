package com.lynas.springsecurityjwt

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity
data class AppUser(
        @Id @GeneratedValue
        val id: Long?,
        @Column(name = "username", unique = true, nullable = false)
        val username: String,
        val password: String,
        val authorities: String
)