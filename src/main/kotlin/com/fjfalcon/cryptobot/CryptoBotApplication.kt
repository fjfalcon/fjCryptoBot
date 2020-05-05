package com.fjfalcon.cryptobot

import com.fjfalcon.cryptobot.bot.BotProperties
import com.fjfalcon.cryptobot.coinmarketcap.ApiProperties
import com.fjfalcon.cryptobot.fns.FNSProperties
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.ComponentScan
import org.springframework.scheduling.annotation.EnableScheduling
import org.telegram.telegrambots.ApiContextInitializer

@SpringBootApplication
@ComponentScan(basePackages = ["com.fjfalcon"])
@EnableConfigurationProperties(ApiProperties::class, BotProperties::class, FNSProperties::class)
@EnableScheduling
class CryptoBotApplication

fun main(args: Array<String>) {
    ApiContextInitializer.init()
    SpringApplication.run(CryptoBotApplication::class.java, *args)
}
