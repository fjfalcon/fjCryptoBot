package com.fjfalcon.cryptobot

import io.micronaut.runtime.Micronaut
import org.telegram.telegrambots.ApiContextInitializer

object CryptoBotApplication {

    @JvmStatic
    fun main(args: Array<String>) {
        ApiContextInitializer.init()
        Micronaut.build()
            .packages("com.fjfalcon.cryptobot")
            .mainClass(CryptoBotApplication.javaClass)
            .start()
    }
}
