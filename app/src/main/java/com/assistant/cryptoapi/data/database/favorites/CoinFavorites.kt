package com.catching.pucks.database.DataBase

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "coinFavorites")
data class CoinDB(

    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val coinId: Int,
    val is_favorites: Boolean,
    val cmc_rank: String,
    val price: String,
    val market_cap: Double,
    val percent_change_24h: Double,
    val percent_change_1h: Double,
    val percent_change_7d: Double,
    val percent_change_30d: Double
)



