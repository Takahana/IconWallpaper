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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import tech.takahana.iconwallpaper.android.onbording.R

@Composable
fun AppBar() {
    TopAppBar(
        title = { Text(stringResource(R.string.page_title)) },
        navigationIcon = {
            IconButton(onClick = { /* do something */ }) {
                Icon(Icons.Filled.Menu, contentDescription = stringResource(R.string.open_drawer))
            }
        },
        actions = {
            IconButton(onClick = { /* do something */ }) {
                Icon(Icons.Filled.Notifications, contentDescription = stringResource(R.string.notifications))
            }
            IconButton(onClick = { /* do something */ }) {
                Icon(Icons.Filled.Share, contentDescription = stringResource(R.string.share))
            }
            IconButton(onClick = { /* do something */ }) {
                Icon(Icons.Filled.Search, contentDescription = stringResource(R.string.search))
            }
        }
    )
}

@Preview(showSystemUi = true)
@Composable
fun PreviewAppBar() {
    AppBar()
}
