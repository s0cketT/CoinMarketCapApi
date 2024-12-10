package com.catching.pucks.database.DataBase

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao{
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertUser(user: UserDB)

    @Update
    suspend fun updateUser(user: UserDB)

    @Delete
    suspend fun deleteUser(user: UserDB)

    @Query("SELECT * FROM user")
    fun getAllUsers(): Flow<List<UserDB>>

}
