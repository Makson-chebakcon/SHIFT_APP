package com.example.shift_app.domain

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shift_app.data.model.UserModel
import com.example.shift_app.data.retrofit.RetrofitClient
import com.example.shift_app.domain.AppState.*
import com.example.shift_app.domain.services.ApiService
import com.example.shift_app.domain.services.UserDataService
import kotlinx.coroutines.launch

class UserViewModel: ViewModel() {

    private val apiService = ApiService(RetrofitClient.service)
    private val userDataService = UserDataService()

    private val _uiState = MutableLiveData<AppState<List<UserModel>>>()

    val uiState: LiveData<AppState<List<UserModel>>> = _uiState

    private val _selectedUser = MutableLiveData<UserModel?>()
    val selectedUser: LiveData<UserModel?> = _selectedUser


    fun loadUsers(context: Context) {
        _uiState.value = AppState.Loading
        viewModelScope.launch {
            _uiState.value = userDataService.getAll(context)
        }
    }

    fun selectUser(user: UserModel) {
        _selectedUser.value = user
    }

    fun updateUsers(context: Context) {
        _uiState.value = AppState.Loading
        viewModelScope.launch {
            when (val apiResult = apiService.loadUsers(10)) {
                is AppState.Success -> {
                    val insertResult = userDataService.insertAll(apiResult.data, context)
                    _uiState.value = userDataService.getAll(context)
                }
                is AppState.Error -> {
                    val localData = userDataService.getAll(context)
                    _uiState.value = when (localData) {
                        is AppState.Success -> AppState.PartialSuccess(
                            data = localData.data,
                            error = apiResult.throwable
                        )
                        else -> apiResult
                    }
                }
                else -> Unit
            }
        }
    }




}