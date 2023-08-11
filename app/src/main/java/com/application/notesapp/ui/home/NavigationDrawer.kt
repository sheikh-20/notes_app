package com.application.notesapp.ui.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Alarm
import androidx.compose.material.icons.outlined.Archive
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material.icons.outlined.Lightbulb
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.application.notesapp.R

sealed class DrawerScreen(val icon: ImageVector, val title: String) {
    object Notes: DrawerScreen(icon = Icons.Outlined.Lightbulb, title = "Note")
    object Reminder: DrawerScreen(icon = Icons.Outlined.Alarm, title = "Reminder")
    object Archive: DrawerScreen(icon = Icons.Outlined.Archive, title = "Archive")
    object Trash: DrawerScreen(icon = Icons.Outlined.Delete, title = "Delete")
    object Settings: DrawerScreen(icon = Icons.Outlined.Settings, title = "Settings")
}

private val screens = listOf(DrawerScreen.Notes, DrawerScreen.Reminder, DrawerScreen.Archive, DrawerScreen.Trash, DrawerScreen.Settings)

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun NavigationDrawer(modifier: Modifier = Modifier,
                     currentScreen: String = "",
                     navigationDrawer: (String) -> Unit = {}) {

    Column(modifier = modifier.padding(16.dp)) {
        Text(text = stringResource(id = R.string.app_name),
            modifier = modifier.padding(10.dp),style = MaterialTheme.typography.titleLarge)

        LazyColumn(verticalArrangement = Arrangement.spacedBy(4.dp)) {
            items(screens) {
                Card(modifier = modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(50),
                    onClick = { navigationDrawer(it.title) },
                    colors = CardDefaults.cardColors(containerColor = if (currentScreen == it.title) MaterialTheme.colorScheme.primaryContainer  else Color.Transparent)) {
                    Row(modifier = modifier.padding(horizontal = 16.dp, vertical = 10.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                        Icon(imageVector = it.icon, contentDescription = null)
                        Text(text = it.title, style = MaterialTheme.typography.bodyMedium)
                    }
                }
            }
        }
    }
}