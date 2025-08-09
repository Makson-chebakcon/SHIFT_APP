package com.example.shift_app.data.model

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_table")
data class UserModel(

    @PrimaryKey val phone: String,

    @Embedded val name: Name,
    val email: String,
    @Embedded val picture: Picture,
    @Embedded val location: Location,

)

data class Name(
    val title: String,
    val first: String,
    val last: String
)