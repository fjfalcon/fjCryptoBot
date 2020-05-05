package com.fjfalcon.cryptobot.fns

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding

@ConstructorBinding
@ConfigurationProperties(prefix = "fns")
data class FNSProperties(val device: String, val os: String, val login: String, val password: String)
