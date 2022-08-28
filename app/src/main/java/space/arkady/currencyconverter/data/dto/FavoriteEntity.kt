package space.arkady.currencyconverter.data.dto

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "currency_favorite")
data class FavoriteEntity(
    @PrimaryKey
    val name_currency: String,
    @ColumnInfo(name = "value_currency")
    val value_currency: Double,
    @ColumnInfo(name = "status_favorite")
    val status_favorite: Boolean
) {

}
