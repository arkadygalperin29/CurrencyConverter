package space.arkady.currencyconverter.data.dto

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [FavoriteEntity::class], version = 1)
abstract class FavoriteDatabase: RoomDatabase() {

    abstract fun getFavoriteDao(): FavoriteDao
}