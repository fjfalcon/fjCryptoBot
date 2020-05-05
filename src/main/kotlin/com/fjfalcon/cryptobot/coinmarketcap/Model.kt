package com.fjfalcon.cryptobot.coinmarketcap

import com.fasterxml.jackson.annotation.JsonProperty

data class Coin(
    @JsonProperty("circulating_supply")
    val circulatingSupply: Double,
    @JsonProperty("cmc_rank")
    val cmcRank: Int,
    @JsonProperty("date_added")
    val dateAdded: String,
    val id: Int,
    @JsonProperty("last_updated")
    val lastUpdated: String,
    @JsonProperty("max_supply")
    val maxSupply: Any?,
    val name: String,
    @JsonProperty("num_market_pairs")
    val numMarketPairs: Int,
    val platform: Any?,
    val quote: Map<String, Price>,
    val slug: String,
    val symbol: String,
    val tags: List<String>,
    @JsonProperty("total_supply")
    val totalSupply: Double
)

data class Price(
    @JsonProperty("last_updated")
    val lastUpdated: String?,
    @JsonProperty("market_cap")
    val marketCap: Double?,
    @JsonProperty("percent_change_1h")
    val percentChange1h: Any?,
    @JsonProperty("percent_change_24h")
    val percentChange24h: Double?,
    @JsonProperty("percent_change_7d")
    val percentChange7d: Double?,
    @JsonProperty("price")
    val amount: Double,
    @JsonProperty("volume_24h")
    val volume24h: Double?
)

data class ApiResponse(val status: ApiResponseStatus, val data: List<Coin>)

data class ApiResponseStatus(
    @JsonProperty("credit_count")
    val creditCount: Int,
    val elapsed: Int,
    @JsonProperty("error_code")
    val errorCode: Int,
    @JsonProperty("error_message")
    val errorMessage: Any?,
    val notice: Any?,
    val timestamp: String
)
