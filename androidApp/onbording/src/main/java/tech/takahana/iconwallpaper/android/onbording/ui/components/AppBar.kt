package tech.takahana.iconwallpaper.android.onbording.ui.components

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Share
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun AppBar() {
    TopAppBar(
        title = { Text("Page title") },
        navigationIcon = {
            IconButton(onClick = { /* do something */ }) {
                Icon(Icons.Filled.Menu, contentDescription = "Open drawer")
            }
        },
        actions = {
            IconButton(onClick = { /* do something */ }) {
                Icon(Icons.Filled.Notifications, contentDescription = "Notifications")
            }
            IconButton(onClick = { /* do something */ }) {
                Icon(Icons.Filled.Share, contentDescription = "Share")
            }
            IconButton(onClick = { /* do something */ }) {
                Icon(Icons.Filled.Search, contentDescription = "Search")
            }
        }
    )
}

@Preview(showSystemUi = true)
@Composable
fun PreviewAppBar() {
    AppBar()
}
