package com.fjfalcon.cryptobot.fns.service

import com.fjfalcon.cryptobot.fns.FNSProperties
import com.fjfalcon.cryptobot.fns.model.ApiResponse
import com.fjfalcon.cryptobot.fns.model.Requisites
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpMethod
import org.springframework.stereotype.Component
import org.springframework.util.MultiValueMap
import org.springframework.web.client.HttpStatusCodeException
import org.springframework.web.client.RestTemplate
import org.springframework.web.client.exchange
import org.springframework.web.util.UriComponentsBuilder

@Component
class ApiService(private val restTemplate: RestTemplate, private val fnsProperties: FNSProperties) {
    private val dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss").withZone(ZoneId.of("Europe/Moscow"))

    fun getCheckExists(requisites: Requisites): ApiResponse {
        val builder =
            UriComponentsBuilder.fromHttpUrl("https://proverkacheka.nalog.ru:9999/v1/ofds/*/inns/*/fss/${requisites.fiscalNumber}/operations/1/tickets/${requisites.fiscalDocumentId}")
                .queryParam("fiscalSign", requisites.fiscalId)
                .queryParam("date", dateTimeFormatter.format(requisites.time))
                .queryParam("sum", (requisites.cost * 100).toInt())

        val entity = HttpEntity<MultiValueMap<String, String>>(generateHeaders())
        return try {

            val response = restTemplate.exchange(
                builder.toUriString(),
                HttpMethod.GET,
                entity,
                ApiResponse::class.java
            )

            ApiResponse(null, null, response.statusCode)
        } catch (e: HttpStatusCodeException) {
            ApiResponse(error = e.responseBodyAsString, httpStatus = e.statusCode, document = null)
        }
    }

    fun getCheckData(requisites: Requisites): ApiResponse {
        val builder =
            UriComponentsBuilder.fromHttpUrl("https://proverkacheka.nalog.ru:9999/v1/inns/*/kkts/*/fss/${requisites.fiscalNumber}/tickets/${requisites.fiscalDocumentId}")
                .queryParam("fiscalSign", requisites.fiscalId)
                .queryParam("sendToEmail", "no")
        val entity = HttpEntity<MultiValueMap<String, String>>(generateHeaders())
        return try {

            val response = restTemplate.exchange<ApiResponse>(
                builder.toUriString(),
                HttpMethod.GET,
                entity
            )

            val apiResponse = response.body
            if (apiResponse != null) {
                apiResponse.httpStatus = response.statusCode
                return apiResponse
            }

            return ApiResponse(null, null, response.statusCode)
        } catch (e: HttpStatusCodeException) {
            ApiResponse(error = e.responseBodyAsString, httpStatus = e.statusCode, document = null)
        }
    }

    private fun generateHeaders(): HttpHeaders {
        val headers = HttpHeaders()

        headers.set("device-id", fnsProperties.device)
        headers.set("device-os", fnsProperties.os)
        headers.setBasicAuth(fnsProperties.login, fnsProperties.password)

        return headers
    }
}
