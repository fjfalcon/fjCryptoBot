package com.fjfalcon.cryptobot.fns.mapper

import com.fjfalcon.cryptobot.fns.model.ItemDtoV2
import com.fjfalcon.cryptobot.fns.model.ReceiptDto
import com.fjfalcon.cryptobot.fns.model.ReceiptDtoV2
import java.math.BigDecimal
import org.springframework.stereotype.Component

@Component
class ReceiptDtoMapper {

    fun toDtoV2(receiptDto: ReceiptDto?): ReceiptDtoV2 {
        val receiptDtoV2 = ReceiptDtoV2(getBigDecimal(receiptDto?.totalSum ?: 0L))

        receiptDto?.items?.forEach { item ->
            repeat(item.quantity) {
                receiptDtoV2.items.add(ItemDtoV2(getBigDecimal(item.price), item.name))
            }
        }

        return receiptDtoV2
    }

    private fun getBigDecimal(price: Long): BigDecimal =
        BigDecimal.valueOf(price).divide(BigDecimal.valueOf(100)).setScale(2)
}
