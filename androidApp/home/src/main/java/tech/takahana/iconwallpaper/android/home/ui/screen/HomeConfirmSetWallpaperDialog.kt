package tech.takahana.iconwallpaper.android.home.ui.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import tech.takahana.iconwallpaper.android.core.ui.components.DialogBackground
import tech.takahana.iconwallpaper.android.core.ui.theme.IconWallPaperTheme
import tech.takahana.iconwallpaper.android.home.R
import tech.takahana.iconwallpaper.uilogic.home.FakeHomeConfirmUiLogic
import tech.takahana.iconwallpaper.uilogic.home.HomeConfirmUiLogic
import tech.takahana.iconwallpaper.uilogic.home.PlatformSetWallpaperTargetUiModel

@Composable
fun HomeConfirmSetWallpaperDialog(
    uiLogic: HomeConfirmUiLogic,
) {
    Dialog(
        onDismissRequest = {
            uiLogic.onSetWallpaperTargetDialogDismissRequested()
        },
        content = {
            DialogBackground {
                Column(
                    modifier = Modifier.padding(top = 16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = stringResource(R.string.home_confirm_set_wallpaper_target),
                        style = MaterialTheme.typography.h5
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Divider()
                    HomeConfirmSetWallpaperTargetItem(
                        text = stringResource(R.string.home_confirm_set_wallpaper_to_home),
                        modifier = Modifier.clickable {
                            uiLogic.onClickedSetWallpaperTarget(PlatformSetWallpaperTargetUiModel.Home)
                        },
                    )
                    Divider()
                    HomeConfirmSetWallpaperTargetItem(
                        text = stringResource(R.string.home_confirm_set_wallpaper_to_lock),
                        modifier = Modifier.clickable {
                            uiLogic.onClickedSetWallpaperTarget(PlatformSetWallpaperTargetUiModel.Lock)
                        },
                    )
                    Divider()
                    HomeConfirmSetWallpaperTargetItem(
                        text = stringResource(R.string.home_confirm_set_wallpaper_to_home_and_lock),
                        modifier = Modifier.clickable {
                            uiLogic.onClickedSetWallpaperTarget(
                                PlatformSetWallpaperTargetUiModel.HomeAndLock
                            )
                        },
                    )
                    Divider()
                    HomeConfirmSetWallpaperTargetItem(
                        text = stringResource(R.string.home_confirm_set_wallpaper_with_other_app),
                        modifier = Modifier.clickable {
                            uiLogic.onClickedSetWallpaperTarget(
                                PlatformSetWallpaperTargetUiModel.OtherApp
                            )
                        },
                    )
                }
            }
        }
    )
}

@Composable
private fun HomeConfirmSetWallpaperTargetItem(
    text: String,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(48.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(text = text)
    }
}

@Preview(showSystemUi = true, device = Devices.PIXEL_4)
@Composable
private fun PreviewHomeConfirmSetWallpaperDialog() {
    IconWallPaperTheme {
        Surface {
            HomeConfirmSetWallpaperDialog(
                uiLogic = FakeHomeConfirmUiLogic()
            )
        }
    }
}
