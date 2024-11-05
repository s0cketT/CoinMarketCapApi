package com.catching.pucks.database.DataBase

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "coinportfolio")
data class CoinPortfolioDB(

    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val coinId: Int,
    val name: String,
    val cmc_rank: String,
    val symbol: String,
    val averagePrice: String,
    val count: Double,
    val buyPrice: Double
)



