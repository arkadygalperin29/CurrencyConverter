package space.arkady.currencyconverter.presentation.fragments.favoritefragment.favoriteadapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import space.arkady.currencyconverter.R
import space.arkady.currencyconverter.databinding.ItemCurrencyBinding
import space.arkady.currencyconverter.domain.model.CurrencyItem
import space.arkady.currencyconverter.presentation.listeners.DeleteFavoriteClickListener
import space.arkady.currencyconverter.presentation.utils.toFavoriteCurrency
import javax.inject.Inject

class FavoriteListViewHolder @Inject constructor(
    private val binding: ItemCurrencyBinding,
    private val deleteFavoriteCurrency: DeleteFavoriteClickListener
) : RecyclerView.ViewHolder(binding.root) {

    companion object {
        fun newInstance(parent: ViewGroup, deleteFavoriteCurrency: DeleteFavoriteClickListener) =
            FavoriteListViewHolder(
                ItemCurrencyBinding.bind(
                    LayoutInflater.from(parent.context)
                        .inflate(R.layout.item_currency, parent, false)
                ),
                deleteFavoriteCurrency
            )
    }

    fun bindItem(currencyItem: CurrencyItem) {
        with(currencyItem) {
            if (statusFavorite) {
                binding.nameCurrency.text = nameCurrency
                binding.valueCurrency.text = valueCurrency.toString()
                binding.imageFavorite.setImageResource(R.drawable.ic_baseline_star_rate_24)
                binding.imageFavorite.setOnClickListener {
                    deleteFavoriteCurrency.clickAction(currencyItem.toFavoriteCurrency())
                }
            }
        }
    }

}
