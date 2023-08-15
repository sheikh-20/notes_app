package com.application.notesapp.ui.archive

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Menu
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material.icons.outlined.Splitscreen
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ArchiveApp(modifier: Modifier = Modifier) {
    Scaffold(
        topBar = { ArchiveTopAppBar() }, 
        
        ) { paddingValues ->
        ArchiveScreen(modifier = modifier.padding(paddingValues))
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun ArchiveTopAppBar(modifier: Modifier = Modifier) {
    TopAppBar(
        navigationIcon = {
            Icon(imageVector = Icons.Outlined.Menu, contentDescription = null, modifier = modifier.padding(horizontal = 8.dp))
                         },
        title = { Text(text = "Archive") },
        actions = {
            IconButton(onClick = { /*TODO*/ }) {
                Icon(imageVector = Icons.Outlined.Search, contentDescription = null)
            }

            IconButton(onClick = {}) {
                Icon(imageVector = Icons.Outlined.Splitscreen, contentDescription = null)
            }
        }
    )
}