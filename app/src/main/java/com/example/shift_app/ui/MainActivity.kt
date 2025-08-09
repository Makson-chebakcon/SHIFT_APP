package com.example.shift_app.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.shift_app.domain.AppState
import com.example.shift_app.domain.UserViewModel
import com.example.shift_app.ui.screen.UserDetailScreen
import com.example.shift_app.ui.screen.UserListScreen
import com.example.shift_app.ui.theme.SHIFT_APPTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SHIFT_APPTheme {
                val navController = rememberNavController()
                val userViewModel: UserViewModel = viewModel()
                val context = LocalContext.current
                val state = userViewModel.uiState.observeAsState().value

                LaunchedEffect(state) {
                    if (state is AppState.Success && state.data.isEmpty()) {
                        userViewModel.updateUsers(context)
                    }
                }

                NavHost(
                    navController = navController,
                    startDestination = "user_list",
                    modifier = Modifier.fillMaxSize()
                ) {
                    composable("user_list") {
                        UserListScreen(userViewModel) { user ->
                            userViewModel.selectUser(user)
                            navController.navigate("user_detail")
                        }
                    }

                    composable("user_detail") {
                        val user = userViewModel.selectedUser.observeAsState().value
                        user?.let {
                            UserDetailScreen(it) {
                                navController.popBackStack()
                            }
                        }
                    }
                }
            }
        }
    }
}
