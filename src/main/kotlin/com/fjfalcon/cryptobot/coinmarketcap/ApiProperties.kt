package com.fjfalcon.cryptobot.coinmarketcap

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding

@ConstructorBinding
@ConfigurationProperties(prefix = "api")
data class ApiProperties(val token: String)
