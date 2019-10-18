package com.lynas.springsecurityjwt.controller

import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpMethod
import org.springframework.http.MediaType
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.web.client.HttpClientErrorException
import org.springframework.web.client.RestTemplate

@RunWith(SpringRunner::class)
class ProtectedControllerTest {

    /**
     * To test this server must be running
     */

    private lateinit var restTemplate: RestTemplate
    private val authUrl = "http://localhost:8080/auth"
    private val protectedUrl = "http://localhost:8080/protected"
    private val headers = HttpHeaders().apply {
        contentType = MediaType.APPLICATION_JSON
    }
    private val validInput = "{\"username\":\"lynas\",\"password\":\"123456\"}"
    private val invalidInput = "{\"username\":\"lynas\",\"password\":\"\"}"

    @Before
    fun init() {
        restTemplate = RestTemplate().apply { requestFactory = HttpComponentsClientHttpRequestFactory() }
    }

    @Test(expected = HttpClientErrorException.Unauthorized::class)
    fun testGetProtectedResourceFail() {
        restTemplate.getForEntity<String>(protectedUrl, String::class.java)
    }

    @Test(expected = HttpClientErrorException.Unauthorized::class)
    fun testGetProtectedResourceFailWithToken() {
        headers.set("Authorization", "value")
        restTemplate.exchange<String>(protectedUrl, HttpMethod.GET, HttpEntity<Any>(headers), String::class.java)
    }

    @Test
    fun testGetProtectedResourceSuccessWithToken() {
        val requestEntity = HttpEntity(validInput, headers)
        val authToken = restTemplate.postForObject<String>(
                authUrl, requestEntity, String::class.java)
        headers.set("Authorization", authToken)
        restTemplate.exchange<String>(protectedUrl, HttpMethod.GET, HttpEntity<Any>(headers), String::class.java)
    }

}