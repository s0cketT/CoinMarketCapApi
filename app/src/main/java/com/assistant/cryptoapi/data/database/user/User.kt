package com.catching.pucks.database.DataBase

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user")
data class UserDB(

    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val login: String,
    val password: String,
    val money: Double,
)



