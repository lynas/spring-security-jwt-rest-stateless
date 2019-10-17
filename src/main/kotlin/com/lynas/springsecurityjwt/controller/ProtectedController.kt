package com.lynas.springsecurityjwt.controller

import com.lynas.springsecurityjwt.dto.SuccessResponse
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/protected")
class ProtectedController {

    @GetMapping
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    fun hello() = SuccessResponse(true)

}
