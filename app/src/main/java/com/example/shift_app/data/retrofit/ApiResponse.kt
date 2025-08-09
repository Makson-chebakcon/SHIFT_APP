package com.example.shift_app.data.retrofit

import com.example.shift_app.data.model.UserModel

data class ApiResponse(
    val results: List<UserModel>
)