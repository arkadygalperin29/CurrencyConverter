package space.arkady.currencyconverter.presentation.fragments.favoritefragment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import space.arkady.currencyconverter.common.Constants
import space.arkady.currencyconverter.domain.interactor.FavoriteCurrencyInteractor
import space.arkady.currencyconverter.domain.interactor.CurrencyInteractor
import space.arkady.currencyconverter.domain.model.Currency
import space.arkady.currencyconverter.domain.model.CurrencyItem
import space.arkady.currencyconverter.domain.model.FavoriteCurrency
import space.arkady.currencyconverter.presentation.sortmenu.SortingTypes
import space.arkady.currencyconverter.presentation.utils.sortedByAlphabet
import space.arkady.currencyconverter.presentation.utils.sortedByAlphabetReversed
import space.arkady.currencyconverter.presentation.utils.sortedByHighestValue
import space.arkady.currencyconverter.presentation.utils.sortedByLeastValue
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(
    private val favoriteCurrencyInteractor: FavoriteCurrencyInteractor,
    private val currencyInteractor: CurrencyInteractor
) : ViewModel() {

    private var searchCurrency: String = Constants.DEFAULT_CURRENCY

    init {
        sortAlphabet()
    }

    private val _favoriteState: MutableStateFlow<Currency> =
        MutableStateFlow(Currency(null, emptyList()))
    val favoriteState: StateFlow<Currency> = _favoriteState.asStateFlow()

    fun sortCurrency(sortingTypes: SortingTypes) {
        when (sortingTypes) {
            SortingTypes.NAME_UP -> sortAlphabet()
            SortingTypes.NAME_DOWN -> sortByAlphabetReversed()
            SortingTypes.VALUE_UP -> sortByLeastPrice()
            SortingTypes.VALUE_DOWN -> sortByHighestPrice()
        }
    }

    fun deleteFavorite(favoriteCurrency: FavoriteCurrency) {
        viewModelScope.launch {
            favoriteCurrencyInteractor.deleteData(favoriteCurrency)
        }
    }

    fun setSearchValue(searchvalue: String) {
        searchCurrency = searchvalue
        getCurrency(searchvalue).map { currency ->
            _favoriteState.value = currency
        }.launchIn(viewModelScope)
    }

    private fun getCurrency(q: String): Flow<Currency> {
        return favoriteCurrencyInteractor.getAllCurrencyData()
            .combine(currencyInteractor.getDataCurrency(q)) { favorite, popular ->
                val listFavorite: MutableList<String> = mutableListOf()
                favorite.map { favoriteCurrency ->
                    listFavorite.add(favoriteCurrency.nameCurrency)
                }
                val listSortFavorite: MutableList<CurrencyItem> = mutableListOf()
                popular.rates.map { currencyItem ->
                    if (listFavorite.contains(currencyItem.nameCurrency)) {
                        listSortFavorite.add(
                            CurrencyItem(
                                currencyItem.nameCurrency, currencyItem.valueCurrency, true
                            )
                        )
                    }
                }
                Currency(
                    popular.base,
                    listSortFavorite.toList()
                )
            }
    }

    private fun sortAlphabet(q: String = searchCurrency) {
        getCurrency(q).map { currency ->
            _favoriteState.value = currency.sortedByAlphabet()
        }.launchIn(viewModelScope)
    }

    private fun sortByAlphabetReversed(q: String = searchCurrency) {
        getCurrency(q).map { currency ->
            _favoriteState.value = currency.sortedByAlphabetReversed()
        }.launchIn(viewModelScope)
    }

    private fun sortByLeastPrice(q: String = searchCurrency) {
        getCurrency(q).map { currency ->
            _favoriteState.value = currency.sortedByLeastValue()
        }.launchIn(viewModelScope)
    }

    private fun sortByHighestPrice(q: String = searchCurrency) {
        getCurrency(q).map { currency ->
            _favoriteState.value = currency.sortedByHighestValue()
        }.launchIn(viewModelScope)
    }
}