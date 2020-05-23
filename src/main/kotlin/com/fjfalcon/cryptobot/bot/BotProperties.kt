package com.fjfalcon.cryptobot.bot

import io.micronaut.context.annotation.ConfigurationProperties

@ConfigurationProperties("bot")
class BotProperties {
    var name: String? = null
    var token: String? = null
}
