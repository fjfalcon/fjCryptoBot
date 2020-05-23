package com.fjfalcon.cryptobot.fns.service

import com.fjfalcon.cryptobot.fns.model.ApiResponse
import com.fjfalcon.cryptobot.fns.model.Requisites
import io.micronaut.http.HttpRequest
import io.micronaut.http.HttpStatus
import io.micronaut.http.client.RxHttpClient
import io.micronaut.http.client.annotation.Client
import io.micronaut.http.client.exceptions.HttpClientResponseException
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import javax.inject.Inject
import javax.inject.Singleton

const val FNS_URL = "https://proverkacheka.nalog.ru:9999/v1/"

@Singleton
class ApiService @Inject constructor(
    @Client(FNS_URL)
    @FnsClient
    val client: RxHttpClient
) {
    private val dateTimeFormatter =
        DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss").withZone(ZoneId.of("Europe/Moscow"))

    fun getCheckExists(requisites: Requisites): ApiResponse {
        try {
            val response = client.retrieve(
                HttpRequest.GET<ApiResponse>(
                    "/ofds/*/inns/*/fss/${requisites.fiscalNumber}/operations/1/tickets/${requisites.fiscalDocumentId}?fiscalSign=${requisites.fiscalId}&date=${dateTimeFormatter.format(
                        requisites.time
                    )}&sum=${(requisites.cost * 100).toInt()}"
                ),
                ApiResponse::class.java
            ).blockingFirst()
            logger.info("Response is {}", response)
        } catch (e: HttpClientResponseException) {
            return ApiResponse(null, null, e.status)
        }
        return ApiResponse(null, null, HttpStatus.OK)
    }

    fun getCheckData(requisites: Requisites): ApiResponse {
        return try {
            client.retrieve(
                HttpRequest.GET<ApiResponse>(
                    "/inns/*/kkts/*/fss/${requisites.fiscalNumber}/tickets/${requisites.fiscalDocumentId}?fiscalSign=${requisites.fiscalId}&sendToEmail=no"
                ), ApiResponse::class.java
            ).blockingFirst()
        } catch (e: HttpClientResponseException) {
            ApiResponse(error = e.response.reason(), httpStatus = e.status, document = null)
        }
    }

    companion object {
        val logger: Logger = LoggerFactory.getLogger(ApiService::class.java)
    }
}
