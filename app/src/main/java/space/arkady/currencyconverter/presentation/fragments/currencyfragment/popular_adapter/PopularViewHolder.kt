package space.arkady.currencyconverter.presentation.fragments.currencyfragment.popular_adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import space.arkady.currencyconverter.R
import space.arkady.currencyconverter.databinding.ItemCurrencyBinding
import space.arkady.currencyconverter.domain.model.CurrencyItem
import space.arkady.currencyconverter.presentation.listeners.AddFavoriteClickListener
import space.arkady.currencyconverter.presentation.listeners.DeleteFavoriteClickListener
import space.arkady.currencyconverter.presentation.utils.toFavoriteCurrency
import javax.inject.Inject

class PopularViewHolder @Inject constructor(
    private val binding: ItemCurrencyBinding,
    private val addFavoriteCurrency: AddFavoriteClickListener,
    private val deleteFavoriteCurrency: DeleteFavoriteClickListener
) : RecyclerView.ViewHolder(binding.root) {

    companion object {
        fun newInstance(
            parent: ViewGroup,
            addFavoriteCurrency: AddFavoriteClickListener,
            deleteFavoriteCurrency: DeleteFavoriteClickListener
        ) = PopularViewHolder(
            ItemCurrencyBinding.bind(
                LayoutInflater.from(parent.context).inflate(
                    R.layout.item_currency, parent, false
                )
            ), addFavoriteCurrency, deleteFavoriteCurrency
        )


    }

    fun bindItem(currencyItem: CurrencyItem) {
        with(currencyItem) {
            binding.nameCurrency.text = nameCurrency
            binding.valueCurrency.text = valueCurrency.toString()
            if (statusFavorite) {
                binding.imageFavorite.setImageResource(R.drawable.ic_baseline_star_rate_24)
                binding.imageFavorite.setOnClickListener {
                    deleteFavoriteCurrency.clickAction(currencyItem.toFavoriteCurrency())
                    binding.imageFavorite.setImageResource(R.drawable.ic_baseline_star_border_24)
                }
            } else {
                binding.imageFavorite.setImageResource(R.drawable.ic_baseline_star_border_24)
                binding.imageFavorite.setOnClickListener {
                    addFavoriteCurrency.actionClick(currencyItem)
                    binding.imageFavorite.setImageResource(R.drawable.ic_baseline_star_rate_24)
                }
            }
        }
    }
}