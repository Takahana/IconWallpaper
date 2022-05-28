package tech.takahana.iconwallpaper.android.core.ui.components

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun DialogBackground(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit,
) {

    Surface(
        color = MaterialTheme.colors.background,
        modifier = modifier,
    ) {
        content()
    }
}
