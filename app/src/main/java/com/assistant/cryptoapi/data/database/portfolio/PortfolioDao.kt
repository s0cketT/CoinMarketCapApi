package com.catching.pucks.database.DataBase

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface PortfolioDao{
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(coinPortfolio: CoinPortfolioDB)

    @Update
    suspend fun update(coinPortfolio: CoinPortfolioDB)

    @Delete
    suspend fun delete(coinPortfolio: CoinPortfolioDB)

    @Query("SELECT * FROM coinportfolio")
    fun getAllUsers(): Flow<List<CoinPortfolioDB>>

}
