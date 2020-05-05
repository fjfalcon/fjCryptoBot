package com.fjfalcon.cryptobot.bot

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding

@ConstructorBinding
@ConfigurationProperties(prefix = "bot")
data class BotProperties(val name: String, val token: String)
