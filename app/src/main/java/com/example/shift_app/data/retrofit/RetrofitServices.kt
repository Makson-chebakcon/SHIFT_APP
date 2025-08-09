package com.example.shift_app.data.retrofit

import com.example.shift_app.data.model.UserModel
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitServices {

    @GET("api/")
    suspend fun getRandomUser(@Query("results") count: Int): ApiResponse
}