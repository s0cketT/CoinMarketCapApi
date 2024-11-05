package com.catching.pucks.database.DataBase

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface CoinDao{
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(coin: CoinDB)

    @Update
    suspend fun update(coin: CoinDB)

    @Delete
    suspend fun delete(coin: CoinDB)

    @Query("SELECT * FROM coinFavorites")
    fun getAllUsers(): Flow<List<CoinDB>>

}
