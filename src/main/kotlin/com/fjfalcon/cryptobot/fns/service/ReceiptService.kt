package com.fjfalcon.cryptobot.fns.service

import com.fjfalcon.cryptobot.fns.mapper.ReceiptDtoMapper
import com.fjfalcon.cryptobot.fns.model.ReceiptData
import com.fjfalcon.cryptobot.fns.model.ReceiptState
import com.fjfalcon.cryptobot.fns.model.Requisites
import io.micronaut.http.HttpStatus
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import javax.inject.Singleton

val logger: Logger = LoggerFactory.getLogger(ReceiptService::class.java)

@Singleton
class ReceiptService(
    private val apiService: ApiService,
    private val receiptDtoMapper: ReceiptDtoMapper
) {
    private val pipeline = mapOf(
        Pair(ReceiptState.NEW, ::parseNewState),
        Pair(ReceiptState.REGISTERED, ::parseRegistered),
        Pair(ReceiptState.FIRST_TIME_SENT, ::parseRegistered),
        Pair(ReceiptState.DONE, ::done)
    )

    fun newReceipt(requisites: Requisites): ReceiptData {
        return ReceiptData(id = null, requisites = requisites, receiptState = ReceiptState.NEW)
    }

    fun process(receiptData: ReceiptData): ReceiptData {
        return pipeline.getValue(receiptData.receiptState).invoke(receiptData)
    }

    private fun parseNewState(data: ReceiptData): ReceiptData {
        val response = apiService.getCheckExists(data.requisites!!)

        logger.info("{}", response)
        data.receiptState = ReceiptState.REGISTERED

        return data
    }

    private fun parseRegistered(data: ReceiptData): ReceiptData {
        val response = apiService.getCheckData(data.requisites!!)

        logger.info("{}", response)
        if (response.error == null && response.httpStatus == HttpStatus.ACCEPTED) {
            data.receiptState = ReceiptState.FIRST_TIME_SENT
        } else if (response.error == null && response.httpStatus == HttpStatus.OK) {
            data.receiptDto = response.document?.receipt
            data.receiptDtoV2 = receiptDtoMapper.toDtoV2(data.receiptDto)

            data.receiptState = ReceiptState.DONE
        } else {
            data.receiptState = ReceiptState.FAIL
        }

        return data
    }

    private fun done(data: ReceiptData): ReceiptData {
        return data
    }
}
