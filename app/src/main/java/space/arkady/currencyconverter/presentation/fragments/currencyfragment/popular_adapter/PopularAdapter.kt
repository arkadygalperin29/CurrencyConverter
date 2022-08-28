package space.arkady.currencyconverter.presentation.fragments.currencyfragment.popular_adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import space.arkady.currencyconverter.domain.model.CurrencyItem
import space.arkady.currencyconverter.presentation.listeners.AddFavoriteClickListener
import space.arkady.currencyconverter.presentation.listeners.DeleteFavoriteClickListener

class PopularAdapter(
    private val addFavoriteCurrency: AddFavoriteClickListener,
    private val deleteFavoriteCurrency: DeleteFavoriteClickListener
) : RecyclerView.Adapter<PopularViewHolder>() {

    private var items: List<CurrencyItem> = emptyList()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PopularViewHolder {
        return PopularViewHolder.newInstance(parent, addFavoriteCurrency, deleteFavoriteCurrency)
    }

    override fun onBindViewHolder(holder: PopularViewHolder, position: Int) {
        holder.bindItem(items[position])
    }

    override fun getItemCount(): Int = items.size

    fun submitItem(listItem: List<CurrencyItem>) {
        items = listItem
        notifyDataSetChanged()
    }
}