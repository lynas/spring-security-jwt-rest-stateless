package com.lynas.springsecurityjwt

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import springfox.documentation.builders.ParameterBuilder
import springfox.documentation.builders.PathSelectors
import springfox.documentation.builders.RequestHandlerSelectors
import springfox.documentation.schema.ModelRef
import springfox.documentation.service.ApiInfo
import springfox.documentation.service.Parameter
import springfox.documentation.spi.DocumentationType
import springfox.documentation.spring.web.plugins.Docket
import springfox.documentation.swagger2.annotations.EnableSwagger2
import java.util.*
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import springfox.documentation.annotations.ApiIgnore
import springfox.documentation.service.Contact


@SpringBootApplication
@EnableSwagger2
class SpringSecurityJwtApplication {

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
                "1.0", "", Contact("","",""), "", "", listOf())
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
class DemoController {
	@GetMapping("/demo")
	fun index(): String {
		return "demo"
	}
}