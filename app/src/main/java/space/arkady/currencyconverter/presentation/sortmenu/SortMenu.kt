package space.arkady.currencyconverter.presentation.sortmenu

import android.content.Context
import android.view.MenuItem
import android.view.View
import android.widget.PopupMenu
import space.arkady.currencyconverter.R
import space.arkady.currencyconverter.presentation.listeners.SortListener

abstract class SortMenu {
    companion object {
        fun sortMenu(view: View, menuRes: Int, sortListener: SortListener, context: Context) {
            val popupMenu = PopupMenu(context, view)
            popupMenu.menuInflater.inflate(menuRes, popupMenu.menu)
            popupMenu.setOnMenuItemClickListener { menuItem: MenuItem ->
                when (menuItem.itemId) {
                    R.id.sortByAlphabet -> {
                        sortListener.clickAction(SortingTypes.NAME_UP)
                    }
                    R.id.sortByAlphabetReverse -> {
                        sortListener.clickAction(SortingTypes.NAME_DOWN)

                    }
                    R.id.sortByPriceLowest -> {
                        sortListener.clickAction(SortingTypes.VALUE_UP)
                    }
                    R.id.sortByPriceHighest -> {
                        sortListener.clickAction(SortingTypes.VALUE_DOWN)
                    }
                }
                return@setOnMenuItemClickListener true
            }
            popupMenu.show()

            val popup = PopupMenu::class.java.getDeclaredField("mPopup")
            popup.isAccessible = true
            val menu = popup.get(popupMenu)
            menu.javaClass.getDeclaredMethod("setForceShowIcon", Boolean::class.java)
                .invoke(menu, true)

        }
    }
}