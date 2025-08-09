package com.example.shift_app.domain

sealed class AppState<out T> {
    object Loading : AppState<Nothing>()
    data class Success<T>(val data: T) : AppState<T>()
    data class Error(val throwable: Throwable) : AppState<Nothing>()

    data class PartialSuccess<out T>(val data: T, val error: Throwable) : AppState<T>()
}