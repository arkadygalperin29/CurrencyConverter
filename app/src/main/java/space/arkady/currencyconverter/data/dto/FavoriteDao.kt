package space.arkady.currencyconverter.data.dto

import androidx.room.*
import kotlinx.coroutines.flow.Flow


@Dao
interface FavoriteDao {

    @Query("SELECT * FROM currency_favorite")
    fun getAllFavorites(): Flow<List<FavoriteEntity>>

    @Insert
    suspend fun saveFavorite(favoriteEntity: FavoriteEntity)

    @Delete
    suspend fun deleteFavorite(favoriteEntity: FavoriteEntity)

    @Update
    suspend fun updateFavorite(favoriteEntity: FavoriteEntity)
}