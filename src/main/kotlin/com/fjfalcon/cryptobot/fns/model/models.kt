package com.fjfalcon.cryptobot.fns.model

import java.math.BigDecimal
import org.springframework.http.HttpStatus

data class ApiResponse(var document: Document?, var error: String?, var httpStatus: HttpStatus?)
data class Document(var receipt: ReceiptDto?)

data class ReceiptDto(
    var rawData: String?,
    var taxationType: Int?,
    var fiscalSign: Long?,
    var receiptCode: Int?,
    var items: MutableList<ItemDto>,
    var retailAddress: String?,
    var operator: String?,
    var fiscalDocumentNumber: Int?,
    var kktRegId: String?,
    var dateTime: String?,
    var totalSum: Long?,
    var shiftNumber: Long?,
    var user: String?,
    var userInn: String?,
    var cashTotalSum: Long?,
    var ecashTotalSum: Long?,
    var requestNumber: Long?,
    var nds18: Long?,
    var operationType: Int?,
    var fiscalDriveNumber: String?
)

data class ItemDto(
    var price: Long,
    var name: String,
    var nds18: Long?,
    var sum: Long?,
    var quantity: Int
)

data class ReceiptDtoV2(
    val totalSum: BigDecimal,
    var items: MutableList<ItemDtoV2> = mutableListOf()
)

data class ItemDtoV2(
    val price: BigDecimal,
    val name: String
)
