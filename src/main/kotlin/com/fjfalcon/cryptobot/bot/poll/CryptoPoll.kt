package com.fjfalcon.cryptobot.bot.poll

import com.fjfalcon.cryptobot.bot.BotProperties
import com.fjfalcon.cryptobot.bot.handler.CheckHandler
import com.fjfalcon.cryptobot.bot.handler.CoinsHandler
import com.fjfalcon.cryptobot.bot.handler.PriceHandler
import com.fjfalcon.cryptobot.coinmarketcap.ApiKeeper
import org.springframework.stereotype.Component
import org.telegram.telegrambots.bots.TelegramLongPollingBot
import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import org.telegram.telegrambots.meta.api.objects.Update

@Component
class CryptoPoll constructor(
    private val botProperties: BotProperties,
    private val apiKeeper: ApiKeeper,
    private val coinsHandler: CoinsHandler,
    private val priceHandler: PriceHandler,
    private val checkHandler: CheckHandler
) : TelegramLongPollingBot() {

    override fun getBotToken(): String = botProperties.token
    override fun getBotUsername(): String = botProperties.name
    override fun onUpdateReceived(update: Update) {
        if (update.hasMessage() && update.message.hasText()) {
            parseUpdate(update, update.message.text)
        }
    }

    private fun parseUpdate(update: Update, text: String) = when {
        containsCoin(text) -> sendText(update, coinsHandler.parseText(update, text))
        containsPriceWithCrypto(text) || containsCryptoName(text) -> sendText(
            update,
            priceHandler.parseText(update, text)
        )
        startsWithCheck(text) -> sendText(update, checkHandler.parseText(update, text))
        else -> Unit
    }

    private fun sendText(update: Update, text: String) {
        if (text.isBlank())
            return
        val message: SendMessage = SendMessage()
            .setChatId(update.message.chatId!!)
            .setText(text)
        sendApiMethod(message)
    }

    private fun containsCoin(text: String) = text == "/coins"
    private fun containsPriceWithCrypto(text: String) = text.startsWith("/price") &&
            apiKeeper.result.containsKey(text.substring(7).toLowerCase())

    private fun containsCryptoName(text: String) =
        apiKeeper.result.containsKey(text.substring(1).toLowerCase())

    private fun startsWithCheck(text: String) = text.startsWith("/check")
}
