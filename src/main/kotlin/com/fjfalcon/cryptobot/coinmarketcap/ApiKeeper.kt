package com.fjfalcon.cryptobot.coinmarketcap

import io.micronaut.scheduling.annotation.Scheduled
import javax.inject.Singleton

/**
 * Created by fjfalcon on 14.05.17.
 */
@Singleton
class ApiKeeper(private val client: ApiClient) {
    internal var result = emptyMap<String, Coin>()

    @Scheduled(fixedDelay = "5h")
    fun update() {
        val tmp = client.makeCoinMarketCapApiRequest()
        if (tmp != null) {
            result = tmp.data.map { it.symbol.toLowerCase() to it }.toMap()
        }
    }
}
