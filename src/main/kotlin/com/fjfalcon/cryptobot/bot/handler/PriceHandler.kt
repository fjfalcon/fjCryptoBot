package com.fjfalcon.cryptobot.bot.handler

import com.fjfalcon.cryptobot.coinmarketcap.ApiKeeper
import com.fjfalcon.cryptobot.coinmarketcap.Coin
import org.telegram.telegrambots.meta.api.objects.Update
import java.math.BigDecimal
import java.math.RoundingMode
import javax.inject.Singleton

@Singleton
class PriceHandler(private val apiKeeper: ApiKeeper) : Handler {
    override fun parseText(update: Update, text: String): String =
        prepareText(
            apiKeeper.result[text.substring(
                if (text.startsWith("/price"))
                    7
                else
                    1
            )
                .toLowerCase()]
        )

    private fun prepareText(coin: Coin?): String {
        if (coin == null) {
            return ""
        }
        val price = coin.quote["RUB"] ?: return ""
        return String.format(
            "%s (%s),  RUB: %s, 1h change: %s%%, 24h change: %s%%, 7w change: %s%%",
            coin.name,
            coin.symbol,
            scaleValue(price.amount),
            scaleValue(price.percentChange1h),
            scaleValue(price.percentChange24h),
            scaleValue(price.percentChange7d)
        )
    }

    fun scaleValue(value: Any?): BigDecimal = when (value) {
        null -> BigDecimal.ZERO
        is String -> BigDecimal(value).setScale(2, RoundingMode.HALF_UP)
        is Double -> BigDecimal(value).setScale(2, RoundingMode.HALF_UP)
        else -> BigDecimal.ZERO
    }
}
