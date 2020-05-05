package com.fjfalcon.cryptobot.coinmarketcap

import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpMethod
import org.springframework.stereotype.Component
import org.springframework.web.client.RestTemplate
import org.springframework.web.client.exchange

const val AUTH_HEADER = "X-CMC_PRO_API_KEY"
const val COIN_MARKET_API_URL = "https://pro-api.coinmarketcap.com/v1/cryptocurrency/listings/latest?start=1&limit=5000&convert=rub"

@Component
class ApiClient(private val restTemplate: RestTemplate, apiProperties: ApiProperties) {
    private val headers = HttpHeaders()
    init {
        headers.set(AUTH_HEADER, apiProperties.token)
    }

    fun makeCoinMarketCapApiRequest(): ApiResponse? {
        val response = restTemplate.exchange<ApiResponse>(COIN_MARKET_API_URL, HttpMethod.GET, HttpEntity<Object>(headers))
        if (response.body == null) {
            return null
        }
        return response.body
    }
}
