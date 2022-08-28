package space.arkady.currencyconverter.presentation.utils

import space.arkady.currencyconverter.domain.model.Currency
import space.arkady.currencyconverter.domain.model.CurrencyItem
import space.arkady.currencyconverter.domain.model.FavoriteCurrency

fun CurrencyItem.toFavoriteCurrency(): FavoriteCurrency {
    return FavoriteCurrency(
        nameCurrency, valueCurrency, statusFavorite
    )
}

fun Currency.sortedByAlphabet(): Currency {
    return Currency(base, rates.sortedByDescending { currencyItem ->
        currencyItem.nameCurrency
    })
}

fun Currency.sortedByAlphabetReversed(): Currency {
    return Currency(base, rates.sortedBy { currencyItem ->
        currencyItem.nameCurrency
    })
}

fun Currency.sortedByLeastValue(): Currency {
    return Currency(base, rates.sortedBy { currencyItem ->
        currencyItem.valueCurrency
    })
}

fun Currency.sortedByHighestValue(): Currency {
    return Currency(base, rates.sortedByDescending { currencyItem ->
        currencyItem.valueCurrency
    })
}