package com.example.shift_app.domain.services

import android.util.Log
import com.example.shift_app.data.model.UserModel
import com.example.shift_app.data.retrofit.RetrofitClient
import com.example.shift_app.data.retrofit.RetrofitServices
import com.example.shift_app.domain.AppState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class ApiService(private val retrofitServices: RetrofitServices) {


    suspend fun getApiUser(count: Int): Result<List<UserModel>> = runCatching {
        retrofitServices.getRandomUser(count).results
    }

    suspend fun loadUsers(count: Int): AppState<List<UserModel>> {
        return try {
            val response = withContext(Dispatchers.IO) {
                retrofitServices.getRandomUser(count)
            }
            AppState.Success(response.results)
        } catch (e: Exception) {
            Log.e("ApiService", "Ошибка загрузки пользователей", e)
            AppState.Error(e)
        }
    }
}