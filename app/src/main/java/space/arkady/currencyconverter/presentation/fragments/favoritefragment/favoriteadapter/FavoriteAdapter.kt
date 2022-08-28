package space.arkady.currencyconverter.presentation.fragments.favoritefragment.favoriteadapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import space.arkady.currencyconverter.domain.model.CurrencyItem
import space.arkady.currencyconverter.presentation.listeners.DeleteFavoriteClickListener

class FavoriteAdapter(private val deleteFavoriteCurrency: DeleteFavoriteClickListener) :
    RecyclerView.Adapter<FavoriteListViewHolder>() {

    private var items: List<CurrencyItem> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteListViewHolder {
        return FavoriteListViewHolder.newInstance(parent, deleteFavoriteCurrency)
    }

    override fun onBindViewHolder(holder: FavoriteListViewHolder, position: Int) {
        holder.bindItem(items[position])
    }

    override fun getItemCount(): Int = items.size

    fun submitItems(itemList: List<CurrencyItem>) {
        items = itemList
        notifyDataSetChanged()
    }
}