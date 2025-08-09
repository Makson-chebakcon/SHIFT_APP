package com.example.shift_app.ui.components

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.shift_app.data.model.UserModel
import com.example.shift_app.domain.UserViewModel


@Composable
fun UserItem(user: UserModel, onClick: () -> Unit){
    Log.d("UserItem", "Image URL: ${user.picture.large}")
    Card(
        modifier = Modifier.padding(8.dp)
            .fillMaxWidth()
            .clickable { onClick() }
        ,
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Row(modifier = Modifier.padding(16.dp)){
            AsyncImage(
                model = user.picture.large,
                contentDescription = null,
                modifier = Modifier
                    .size(64.dp)
                    .clip(CircleShape)
            )
            Spacer(modifier = Modifier.width(12.dp))
            Column {
                Text("${user.name.first} ${user.name.last}", style = MaterialTheme.typography.titleMedium)
                Text(user.email)
                Text(user.location.city)
            }


        }


    }

}
