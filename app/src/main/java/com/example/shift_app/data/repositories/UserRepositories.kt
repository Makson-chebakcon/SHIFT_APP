package com.example.shift_app.data.repositories

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.shift_app.data.model.UserModel

@Dao
interface UserRepositories {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(users: List<UserModel>): List<Long>

    @Query("SELECT * FROM user_table")
    suspend fun  getAll(): List<UserModel>

    @Update
    suspend fun updateUser(userModel: UserModel)

    @Delete
    suspend fun deleteUser(userModel: UserModel)

    @Query("DELETE FROM user_table")
    suspend fun deleteAll(): Int
}