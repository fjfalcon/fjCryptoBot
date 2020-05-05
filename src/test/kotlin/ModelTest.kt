import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import com.fjfalcon.cryptobot.coinmarketcap.ApiResponse
import org.junit.jupiter.api.Test

class ModelTest {
    private val string = """{
  "status": { 
    "timestamp": "2020-04-26T20:05:15.770Z",
    "error_code": 0,
    "error_message": null,
    "elapsed": 13,
    "credit_count": 1,
    "notice": null
  },
  "data": [
    {
      "id": 1,
      "name": "Bitcoin",
      "symbol": "BTC",
      "slug": "bitcoin",
      "num_market_pairs": 7968,
      "date_added": "2013-04-28T00:00:00.000Z",
      "tags": [
        "mineable"
      ],
      "max_supply": 21000000,
      "circulating_supply": 18346912,
      "total_supply": 18346912,
      "platform": null,
      "cmc_rank": 1,
      "last_updated": "2020-04-26T20:04:48.000Z",
      "quote": {
        "RUB": {
          "price": 570528.7878213427,
          "volume_24h": 2342455555767.9487,
          "percent_change_1h": -0.03526289,
          "percent_change_24h": 1.30465665,
          "percent_change_7d": 6.9685862,
          "market_cap": 10467441463624.846,
          "last_updated": "2020-04-26T20:04:05.000Z"
        }
      }
    },
    {
      "id": 1027,
      "name": "Ethereum",
      "symbol": "ETH",
      "slug": "ethereum",
      "num_market_pairs": 5182,
      "date_added": "2015-08-07T00:00:00.000Z",
      "tags": [
        "mineable"
      ],
      "max_supply": null,
      "circulating_supply": 110682253.1865,
      "total_supply": 110682253.1865,
      "platform": null,
      "cmc_rank": 2,
      "last_updated": "2020-04-26T20:04:32.000Z",
      "quote": {
        "RUB": {
          "price": 14630.55930130712,
          "volume_24h": 1311162670809.6494,
          "percent_change_1h": -0.10386943,
          "percent_change_24h": 0.81577871,
          "percent_change_7d": 8.29527247,
          "market_cap": 1619343268847.3772,
          "last_updated": "2020-04-26T20:04:05.000Z"
        }
      }
    },
    {
      "id": 52,
      "name": "XRP",
      "symbol": "XRP",
      "slug": "xrp",
      "num_market_pairs": 545,
      "date_added": "2013-08-04T00:00:00.000Z",
      "tags": [],
      "max_supply": 100000000000,
      "circulating_supply": 44089620959,
      "total_supply": 99990980257,
      "platform": null,
      "cmc_rank": 3,
      "last_updated": "2020-04-26T20:04:08.000Z",
      "quote": {
        "RUB": {
          "price": 14.61373919919018,
          "volume_24h": 122755703067.16531,
          "percent_change_1h": -0.00844474,
          "percent_change_24h": 0.77655182,
          "percent_change_7d": 2.6920749,
          "market_cap": 644314222085.9752,
          "last_updated": "2020-04-26T20:04:05.000Z"
        }
      }
    }
  ]
}
"""

    @Test
    fun test() {
        val mapper = jacksonObjectMapper()
        mapper.readValue<ApiResponse>(string)
    }
}
