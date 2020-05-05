package com.fjfalcon.cryptobot.fns.model

import java.time.ZonedDateTime

data class Requisites(
    val time: ZonedDateTime,
    val cost: Double,
    val fiscalNumber: Long,
    val fiscalDocumentId: Long,
    val fiscalId: Long,
    val type: Int
)
