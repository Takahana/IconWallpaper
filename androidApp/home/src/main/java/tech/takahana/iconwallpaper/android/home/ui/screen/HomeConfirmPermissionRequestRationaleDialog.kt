package tech.takahana.iconwallpaper.android.home.ui.screen

import androidx.compose.material.AlertDialog
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import tech.takahana.iconwallpaper.android.core.ui.theme.IconWallPaperTheme
import tech.takahana.iconwallpaper.android.home.R

@Composable
fun HomeConfirmPermissionRequestRationaleDialog(
    modifier: Modifier = Modifier,
) {
    AlertDialog(
        onDismissRequest = {
            // TODO
        },
        confirmButton = {
            TextButton(onClick = {
                // TODO
            }) {
                Text(
                    text = stringResource(id = R.string.home_confirm_permission_confirmed),
                    color = MaterialTheme.colors.onBackground
                )
            }
        },
        modifier = modifier,
        dismissButton = {
            TextButton(onClick = {
                // TODO
            }) {
                Text(
                    text = stringResource(id = R.string.cancel),
                    color = MaterialTheme.colors.onBackground
                )
            }
        },
        title = {
            Text(
                text = stringResource(
                    id = R.string.home_confirm_permission_dialog_title
                )
            )
        },
        text = {
            Text(text = stringResource(id = R.string.home_confirm_permission_dialog_text))
        }
    )
}

@Preview(showSystemUi = true)
@Composable
fun PreviewHomeConfirmPermissionDialog() {
    IconWallPaperTheme {
        HomeConfirmPermissionRequestRationaleDialog()
    }
}
