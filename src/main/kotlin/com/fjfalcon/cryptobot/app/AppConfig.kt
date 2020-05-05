package com.fjfalcon.cryptobot.app

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.client.RestTemplate

@Configuration
class AppConfig {
    @Bean
    fun createRestTemplate(): RestTemplate = RestTemplate()
}
