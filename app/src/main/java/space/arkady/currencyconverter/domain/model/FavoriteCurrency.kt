package space.arkady.currencyconverter.domain.model

data class FavoriteCurrency(
    val nameCurrency: String,
    val valueCurrency: Double,
    val statusFavorite: Boolean
)
