package com.fjfalcon.cryptobot.coinmarketcap

import io.micronaut.context.annotation.ConfigurationProperties

@ConfigurationProperties("api")
class ApiProperties {
    var token: String? = null
}
