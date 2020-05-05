package com.fjfalcon.cryptobot.coinmarketcap

import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component

/**
 * Created by fjfalcon on 14.05.17.
 */
@Component
class ApiKeeper(private val client: ApiClient) {
    internal var result = emptyMap<String, Coin>()

    @Scheduled(fixedDelay = 60 * 1000 * 60)
    fun update() {
        val tmp = client.makeCoinMarketCapApiRequest()
        if (tmp != null) {
            result = tmp.data.map { it.symbol.toLowerCase() to it }.toMap()
        }
    }
}
