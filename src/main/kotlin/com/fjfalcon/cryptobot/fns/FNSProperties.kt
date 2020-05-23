package com.fjfalcon.cryptobot.fns

import io.micronaut.context.annotation.ConfigurationProperties

@ConfigurationProperties("fns")
class FNSProperties {
    var os: String? = null
    var device: String? = null
    var login: String? = null
    var password: String? = null
}
