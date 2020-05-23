package com.fjfalcon.cryptobot.bot.handler

import com.fjfalcon.cryptobot.coinmarketcap.ApiKeeper
import com.fjfalcon.cryptobot.coinmarketcap.Coin
import io.micronaut.scheduling.annotation.Scheduled
import org.telegram.telegrambots.meta.api.objects.Update
import javax.inject.Singleton

@Singleton
class CoinsHandler(private val apiKeeper: ApiKeeper) : Handler {
    private var message: String = ""

    override fun parseText(update: Update, text: String): String =
        if (update.message.chat.isUserChat) {
            message
        } else {
            "К сожалению данная команда доступна только в чате бота"
        }

    @Scheduled(initialDelay = "15s", fixedDelay = "10m")
    fun update() {
        message = apiKeeper.result.values.sortedWith(compareBy { it.cmcRank })
            .joinToString("\n", limit = 100, transform = this::jsonObjectToString)
    }

    private fun jsonObjectToString(coin: Coin): String {
        return String.format("%s. %s (%s)", coin.cmcRank, coin.name, coin.symbol)
    }
}
