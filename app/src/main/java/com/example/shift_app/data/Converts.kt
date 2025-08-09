package com.example.shift_app.data

import androidx.room.TypeConverter
import com.example.shift_app.data.model.Location
import com.example.shift_app.data.model.Street
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Converters {

    private val gson = Gson()

    @TypeConverter
    fun fromLocation(location: Location): String = gson.toJson(location)

    @TypeConverter
    fun toLocation(data: String): Location =
        gson.fromJson(data, object : TypeToken<Location>() {}.type)

    @TypeConverter
    fun fromStreet(street: Street): String = gson.toJson(street)

    @TypeConverter
    fun toStreet(data: String): Street =
        gson.fromJson(data, object : TypeToken<Street>() {}.type)
}