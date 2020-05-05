package com.fjfalcon.cryptobot.bot

import com.fjfalcon.cryptobot.bot.poll.CryptoPoll
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import org.telegram.telegrambots.meta.TelegramBotsApi

@Component
class CryptoBot @Autowired constructor(cryptoPoll: CryptoPoll) {
    init {
        val botsApi = TelegramBotsApi()
        botsApi.registerBot(cryptoPoll)
    }
}
