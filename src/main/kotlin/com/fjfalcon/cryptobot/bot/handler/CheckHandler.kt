package com.fjfalcon.cryptobot.bot.handler

import com.fjfalcon.cryptobot.fns.model.ReceiptState
import com.fjfalcon.cryptobot.fns.model.Requisites
import com.fjfalcon.cryptobot.fns.service.ReceiptService
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.telegram.telegrambots.meta.api.objects.Update
import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import javax.inject.Singleton

val logger: Logger = LoggerFactory.getLogger(CheckHandler::class.java)

@Singleton
class CheckHandler(val receiptService: ReceiptService) : Handler {
    val regex = "^t=(.*)&s=(.*)&fn=(.*)&i=(.*)&fp=(.*)&n=(.*)\$".toRegex()

    override fun parseText(update: Update, text: String): String {

        val matchResult = regex.find(text.substring(7))

        if (matchResult != null) {
            val requisites = Requisites(
                ZonedDateTime.parse(
                    matchResult.groupValues[1],
                    if (matchResult.groupValues[1].length == stringLengthWithoutSeconds) dateTimeFormatterWithoutSeconds else dateTimeFormatterWithSeconds
                ),
                matchResult.groupValues[2].toDouble(),
                matchResult.groupValues[3].toLong(),
                matchResult.groupValues[4].toLong(),
                matchResult.groupValues[5].toLong(),
                matchResult.groupValues[6].toInt()
            )

            val data = receiptService.newReceipt(requisites)

            while (data.receiptState != ReceiptState.FAIL && data.receiptState != ReceiptState.DONE) {
                receiptService.process(data)
            }

            logger.info(data.toString())

            if (data.receiptDtoV2 != null) {

                val result = StringBuilder()
                result.append("Полная стоимость: ${data.receiptDtoV2!!.totalSum}")
                data.receiptDtoV2!!.items.forEach { result.append("\n${it.name} - ${it.price}") }
                return result.toString()
            }

            return "No data found"
        }
        return "No data found"
    }

    companion object {
        val dateTimeFormatterWithoutSeconds: DateTimeFormatter =
            DateTimeFormatter.ofPattern("yyyyMMdd'T'HHmm").withZone(ZoneId.of("Europe/Moscow"))
        const val stringLengthWithoutSeconds = 13

        val dateTimeFormatterWithSeconds: DateTimeFormatter =
            DateTimeFormatter.ofPattern("yyyyMMdd'T'HHmmss").withZone(ZoneId.of("Europe/Moscow"))
    }
}
