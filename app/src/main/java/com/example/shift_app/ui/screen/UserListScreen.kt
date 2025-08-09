package com.example.shift_app.ui.screen

import android.app.ProgressDialog.show
import android.widget.Toast
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.shift_app.data.model.UserModel
import com.example.shift_app.domain.AppState
import com.example.shift_app.domain.UserViewModel
import com.example.shift_app.ui.components.UserItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserListScreen(
    viewModel: UserViewModel,
    onUserClick: (user: UserModel) -> Unit
) {
    val state = viewModel.uiState.observeAsState(AppState.Loading).value
    val context = LocalContext.current

    LaunchedEffect(Unit) {
        viewModel.loadUsers(context = context)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Список пользователей") },
                actions = {
                    IconButton(onClick = { viewModel.updateUsers(context = context) }) {
                        Icon(Icons.Default.Refresh, contentDescription = "Обновить")
                    }
                }
            )
        }
    ) { paddingValues ->
        when (state) {
            is AppState.Loading -> {
                CircularProgressIndicator(
                    modifier = Modifier
                        .padding(paddingValues)
                        .padding(16.dp)
                )
            }

            is AppState.Success -> {
                LazyColumn(contentPadding = paddingValues) {
                    items(state.data.size) { index ->
                        val user = state.data[index]
                        UserItem(user = user) { onUserClick(user) }
                    }
                }
            }

            is AppState.PartialSuccess -> {
                Toast.makeText(context,
                    "Ошибка обновления: ${state.error.message ?: "Неизвестная ошибка"}",
                    Toast.LENGTH_LONG
                ).show()

                LazyColumn(contentPadding = paddingValues) {
                    items(state.data.size) { index ->
                        val user = state.data[index]
                        UserItem(user = user) { onUserClick(user) }
                    }
                }
            }

            is AppState.Error -> {
                Toast.makeText(context,
                    "Ошибка: ${state.throwable.message ?: "Неизвестная ошибка"}",
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }
}
