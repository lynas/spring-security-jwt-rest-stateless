package com.lynas.springsecurityjwt

import com.lynas.springsecurityjwt.dto.SuccessResponse
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import springfox.documentation.annotations.ApiIgnore
import springfox.documentation.builders.ParameterBuilder
import springfox.documentation.builders.PathSelectors
import springfox.documentation.builders.RequestHandlerSelectors
import springfox.documentation.schema.ModelRef
import springfox.documentation.service.ApiInfo
import springfox.documentation.service.Contact
import springfox.documentation.service.Parameter
import springfox.documentation.spi.DocumentationType
import springfox.documentation.spring.web.plugins.Docket
import springfox.documentation.swagger2.annotations.EnableSwagger2
import java.util.*


@SpringBootApplication
@EnableSwagger2
class SpringSecurityJwtApplication {

    @Bean
    fun init(appUserRepository: AppUserRepository) = CommandLineRunner {
        appUserRepository.save(AppUser(
                id = null,
                username = "lynas",
                password = "\$2a\$10\$aS/lF2c/9JWPUjDHfJ/zTed1ihGBgfX/7xnGTOM5/lW59X4FHalSi",
                authorities = "ROLE_ADMIN, ROLE_EM PLOYEE, ROLE_MANAGER"))
    }

    @Bean
    fun docket(): Docket {
        val parameter = ParameterBuilder()
                .name("Authorization")
                .description("Authorization token required for requesting secured content")
                .modelRef(ModelRef("string"))
                .parameterType("header")
                .required(false)
                .build()
        val parameterList = ArrayList<Parameter>()
        parameterList.add(parameter)
        return Docket(DocumentationType.SWAGGER_2)
                .globalOperationParameters(parameterList)
                .select()
                .apis(RequestHandlerSelectors.basePackage(javaClass.getPackage().name))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(getApiInfo())
    }

    private fun getApiInfo(): ApiInfo {
        return ApiInfo("Spring Security Jwt",
                "A stateless web application with Spring security & Jwt",
                "1.0", "", Contact("", "", ""), "", "", listOf())
    }
}

fun main(args: Array<String>) {
    runApplication<SpringSecurityJwtApplication>(*args)
}

@Controller
@ApiIgnore
class HomeController {
    @RequestMapping("/")
    fun index(): String {
        return "redirect:swagger-ui.html"
    }
}

@RestController
@RequestMapping("/protected")
class ProtectedController {

    @GetMapping
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    fun hello() = SuccessResponse(true)


}