package space.arkady.currencyconverter.common

class Constants {
    companion object {
        const val BASE_URL = "https://api.apilayer.com/exchangerates_data/"
        const val BASE_API_KEY = "s0PrJ8I5fZ6bOkMPI0fLR73bRwhFaAGi"
        const val DEFAULT_CURRENCY = "USD"
        val BASE_CURRENCY_LIST = listOf("USD", "EUR", "BYN", "UAH", "PLN", "BTC")
    }
}