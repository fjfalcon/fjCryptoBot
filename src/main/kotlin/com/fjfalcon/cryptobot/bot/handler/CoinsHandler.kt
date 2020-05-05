package com.fjfalcon.cryptobot.bot.handler

import com.fjfalcon.cryptobot.coinmarketcap.ApiKeeper
import com.fjfalcon.cryptobot.coinmarketcap.Coin
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component
import org.telegram.telegrambots.meta.api.objects.Update

@Component
class CoinsHandler(private val apiKeeper: ApiKeeper) : Handler {
    private var message: String = ""

    override fun parseText(update: Update, text: String): String =
        if (update.message.chat.isUserChat) {
            message
        } else {
            "К сожалению данная команда доступна только в чате бота"
        }

    @Scheduled(initialDelay = 5 * 1000, fixedDelay = 1000 * 60 * 10)
    fun update() {
        message = apiKeeper.result.values.sortedWith(compareBy { it.cmcRank })
            .joinToString("\n", limit = 100, transform = this::jsonObjectToString)
    }

    private fun jsonObjectToString(coin: Coin): String {
        return String.format("%s. %s (%s)", coin.cmcRank, coin.name, coin.symbol)
    }
}
