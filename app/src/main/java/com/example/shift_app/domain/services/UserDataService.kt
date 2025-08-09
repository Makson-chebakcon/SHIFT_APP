package com.example.shift_app.domain.services

import android.content.Context
import android.database.sqlite.SQLiteException
import android.util.Log
import com.example.shift_app.data.UserDatabase
import com.example.shift_app.data.model.UserModel
import com.example.shift_app.data.repositories.UserRepositories
import com.example.shift_app.domain.AppState


class UserDataService {

    private lateinit var userRepositories: UserRepositories

    suspend fun insertAll(userList: List<UserModel>, context: Context): AppState<Int> {
        return try {
            userRepositories = UserDatabase.getInstance(context).userRepositories()
            val result = userRepositories.insertAll(userList)
            AppState.Success(result.size)
        } catch (e: SQLiteException) {
            Log.e("Database", "Ошибка базы данных: ${e.message}")
            AppState.Error(e)
        } catch (e: Exception) {
            Log.e("Database", "Общая ошибка: ${e.message}")
            AppState.Error(e)
        }
    }

    suspend fun getAll(context: Context): AppState<List<UserModel>> {
        return try {
            userRepositories = UserDatabase.getInstance(context).userRepositories()
            val result = userRepositories.getAll()
            AppState.Success(result)
        } catch (e: SQLiteException) {
            Log.e("Database", "Ошибка базы данных: ${e.message}")
            AppState.Error(e)
        } catch (e: Exception) {
            Log.e("Database", "Общая ошибка: ${e.message}")
            AppState.Error(e)
        }
    }

    suspend fun updateUser(userModel: UserModel, context: Context): AppState<Unit> {
        return try {
            userRepositories = UserDatabase.getInstance(context).userRepositories()
            userRepositories.updateUser(userModel)
            AppState.Success(Unit)
        } catch (e: SQLiteException) {
            Log.e("Database", "Ошибка базы данных: ${e.message}")
            AppState.Error(e)
        } catch (e: Exception) {
            Log.e("Database", "Общая ошибка: ${e.message}")
            AppState.Error(e)
        }
    }

    suspend fun deleteUser(userModel: UserModel, context: Context): AppState<Unit> {
        return try {
            userRepositories = UserDatabase.getInstance(context).userRepositories()
            userRepositories.deleteUser(userModel)
            AppState.Success(Unit)
        } catch (e: SQLiteException) {
            Log.e("Database", "Ошибка базы данных: ${e.message}")
            AppState.Error(e)
        } catch (e: Exception) {
            Log.e("Database", "Общая ошибка: ${e.message}")
            AppState.Error(e)
        }
    }

    suspend fun deleteAll(context: Context): AppState<Int> {
        return try {
            userRepositories = UserDatabase.getInstance(context).userRepositories()
            val deletedCount = userRepositories.deleteAll()
            AppState.Success(deletedCount)
        } catch (e: SQLiteException) {
            Log.e("Database", "Ошибка базы данных: ${e.message}")
            AppState.Error(e)
        } catch (e: Exception) {
            Log.e("Database", "Общая ошибка: ${e.message}")
            AppState.Error(e)
        }
    }
}