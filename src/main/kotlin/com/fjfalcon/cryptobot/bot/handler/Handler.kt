package com.fjfalcon.cryptobot.bot.handler

import org.telegram.telegrambots.meta.api.objects.Update

interface Handler {
    fun parseText(update: Update, text: String): String
}
