package com.fjfalcon.cryptobot.fns.model

import java.math.BigDecimal

data class ReceiptData(
    var id: Long?,
    var requisites: Requisites?,
    var receiptState: ReceiptState,
    var receiptDto: ReceiptDto? = null,
    var receiptDtoV2: ReceiptDtoV2? = null,
    var name: String? = null,
    var personId: String? = null,
    var payed: Boolean? = null,
    var items: MutableList<Item> = ArrayList()
)

data class Item(
    var id: Long?,
    var receiptId: Long?,
    var name: String?,
    var price: BigDecimal?,
    var personId: String?,
    var payed: Boolean?,
    var billId: Long?
)
