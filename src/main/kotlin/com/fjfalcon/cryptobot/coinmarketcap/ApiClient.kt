package com.fjfalcon.cryptobot.coinmarketcap

import io.micronaut.http.HttpRequest
import io.micronaut.http.client.RxHttpClient
import io.micronaut.http.client.annotation.Client
import javax.inject.Inject
import javax.inject.Singleton

const val AUTH_HEADER = "X-CMC_PRO_API_KEY"
const val GET_REQUEST_PART = "latest?start=1&limit=5000&convert=rub"
const val COIN_MARKET_LISTING_API_URL =
    "https://pro-api.coinmarketcap.com/v1/cryptocurrency/listings"

@Singleton
class ApiClient @Inject constructor(
    private val apiProperties: ApiProperties,
    @Client(COIN_MARKET_LISTING_API_URL) val client: RxHttpClient
) {

    fun makeCoinMarketCapApiRequest(): ApiResponse? {
        return client.retrieve(
            HttpRequest.GET<ApiResponse>(GET_REQUEST_PART).header(AUTH_HEADER, apiProperties.token),
            ApiResponse::class.java
        )
            .blockingFirst()
    }
}
