package space.arkady.currencyconverter.data.model

import com.google.gson.annotations.SerializedName

data class CurrencyResponce(
    @SerializedName("base")
    var base: String? = null,
    @SerializedName("date")
    var date: String? = null,
    @SerializedName("rates")
    var rates: Rates? = Rates(),
    @SerializedName("success")
    var success: Boolean? = null,
    @SerializedName("timestamp")
    var timestamp: Int? = null
)
