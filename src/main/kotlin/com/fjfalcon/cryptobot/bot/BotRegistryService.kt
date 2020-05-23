package com.fjfalcon.cryptobot.bot

import com.fjfalcon.cryptobot.bot.poll.CryptoPoll
import io.micronaut.context.event.ApplicationEventListener
import io.micronaut.context.event.StartupEvent
import org.telegram.telegrambots.meta.TelegramBotsApi
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class BotRegistryService @Inject constructor(val cryptoPoll: CryptoPoll) :
    ApplicationEventListener<StartupEvent> {
    override fun onApplicationEvent(event: StartupEvent?) {
        val botsApi = TelegramBotsApi()
        botsApi.registerBot(cryptoPoll)
    }
}
