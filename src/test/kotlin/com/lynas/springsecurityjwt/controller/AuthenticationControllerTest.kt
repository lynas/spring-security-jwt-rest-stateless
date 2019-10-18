package com.lynas.springsecurityjwt.controller

import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.web.client.HttpClientErrorException
import org.springframework.web.client.RestTemplate

@RunWith(SpringRunner::class)
class AuthenticationControllerTest {

    /**
     * To test this server must be running
     */

    private lateinit var restTemplate: RestTemplate
    private val authUrl = "http://localhost:8080/auth"
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
    fun testGetAuthTokenFail() {
        val requestEntity = HttpEntity(invalidInput, headers)
        val response = restTemplate.postForEntity<String>(
                authUrl, requestEntity, String::class.java)
        assertEquals(response.statusCode, 401)
    }

    @Test
    fun testGetAuthTokenSuccess() {
        val requestEntity = HttpEntity(validInput, headers)
        val response = restTemplate.postForEntity<String>(
                authUrl, requestEntity, String::class.java)
        assertEquals(response.statusCodeValue, 200)
    }
}