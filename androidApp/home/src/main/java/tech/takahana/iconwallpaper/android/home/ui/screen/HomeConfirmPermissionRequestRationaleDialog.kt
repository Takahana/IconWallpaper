package tech.takahana.iconwallpaper.android.home.ui.screen

import androidx.compose.material.AlertDialog
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import tech.takahana.iconwallpaper.android.core.CoreResString
import tech.takahana.iconwallpaper.android.core.ui.theme.IconWallPaperTheme
import tech.takahana.iconwallpaper.android.home.R
import tech.takahana.iconwallpaper.uilogic.home.FakeHomeConfirmUiLogic
import tech.takahana.iconwallpaper.uilogic.home.HomeConfirmUiLogic

@Composable
fun HomeConfirmPermissionRequestRationaleDialog(
    uiLogic: HomeConfirmUiLogic,
    modifier: Modifier = Modifier,
) {
    AlertDialog(
        onDismissRequest = {
            uiLogic.onPermissionRequestRationaleDialogDismissRequested()
        },
        confirmButton = {
            TextButton(onClick = {
                uiLogic.onClickedConfirmPermission()
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
                uiLogic.onPermissionRequestRationaleDialogDismissRequested()
            }) {
                Text(
                    text = stringResource(id = CoreResString.cancel),
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
        HomeConfirmPermissionRequestRationaleDialog(uiLogic = FakeHomeConfirmUiLogic())
    }
}
