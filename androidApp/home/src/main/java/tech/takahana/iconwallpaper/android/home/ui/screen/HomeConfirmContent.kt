package tech.takahana.iconwallpaper.android.home.ui.screen

import android.content.Context
import android.graphics.Bitmap
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asAndroidBitmap
import androidx.compose.ui.graphics.drawscope.CanvasDrawScope
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import tech.takahana.iconwallpaper.android.core.Screen
import tech.takahana.iconwallpaper.android.core.ui.theme.IconWallPaperTheme
import tech.takahana.iconwallpaper.android.core.utils.MediaStoreManager
import tech.takahana.iconwallpaper.android.core.utils.wallpaper.WallpaperManagerWrapper
import tech.takahana.iconwallpaper.android.home.R
import tech.takahana.iconwallpaper.android.home.ui.components.StepAnnouncement
import tech.takahana.iconwallpaper.android.home.ui.screen.viewmodel.HomeConfirmViewModel
import tech.takahana.iconwallpaper.uilogic.home.HomeConfirmUiLogic
import tech.takahana.iconwallpaper.uilogic.home.PlatformSetWallpaperTargetUiModel

@Composable
fun HomeConfirmContent(
    modifier: Modifier = Modifier,
    rootNavController: NavHostController,
    viewModel: HomeConfirmViewModel = viewModel(),
    uiLogic: HomeConfirmUiLogic = viewModel.uiLogic,
) {
    val density = LocalDensity.current
    val layoutDirection = LocalLayoutDirection.current
    val applicationContext = requireNotNull(LocalContext.current.applicationContext)
    val localContext = LocalContext.current
    val onDraw: DrawScope.() -> Unit = {
        val canvasSize = size
        drawRect(
            color = Color.Gray,
            size = canvasSize
        )
    }

    val openSetWallpaperDialog: Boolean by uiLogic.openSetWallpaperTargetDialogStateFlow.collectAsState()

    ConstraintLayout(
        modifier = modifier.fillMaxSize()
    ) {
        val (
            stepAnnouncement,
            canvas,
            buttonContainer,
        ) = createRefs()

        StepAnnouncement(
            modifier = Modifier.constrainAs(stepAnnouncement) {
                top.linkTo(parent.top)
            },
            message = stringResource(R.string.home_step3_confirm)
        )
        Canvas(
            modifier = Modifier
                .fillMaxWidth()
                .constrainAs(canvas) {
                    top.linkTo(stepAnnouncement.bottom)
                    bottom.linkTo(buttonContainer.top)
                    height = Dimension.fillToConstraints
                },
            onDraw = onDraw,
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(24.dp)
                .constrainAs(buttonContainer) {
                    top.linkTo(canvas.bottom)
                    bottom.linkTo(parent.bottom)
                },
            horizontalArrangement = Arrangement.Center,
        ) {
            ActionButton(
                textResId = R.string.home_confirm_save_image,
                iconResId = R.drawable.ic_save_24,
                onClick = {
                    // TODO ストレージ書き込みの権限をリクエストする
                    rootNavController.navigate(Screen.WelcomeScreen.route)
                    saveImage(applicationContext, density, layoutDirection, onDraw)
                },
            )
            Spacer(modifier = Modifier.width(40.dp))
            ActionButton(
                textResId = R.string.home_confirm_set_wallpaper,
                iconResId = R.drawable.ic_wallpaper_24,
                onClick = { uiLogic.onClickedSetWallpaper() },
            )
        }
    }

    if (openSetWallpaperDialog) {
        HomeConfirmSetWallpaperDialog(uiLogic)
    }

    LaunchedEffect(key1 = Unit) {
        uiLogic.setWallpaperEffect
            .onEach { target ->
                setWallpaper(
                    applicationContext,
                    localContext,
                    density,
                    layoutDirection,
                    target as PlatformSetWallpaperTargetUiModel,
                    onDraw,
                    onSuccess = {
                        // TODO メッセージを表示
                    },
                    onFailure = {
                        // TODO メッセージを表示
                    },
                )
            }
            .launchIn(this)
    }
}

@Composable
private fun ActionButton(
    @StringRes textResId: Int,
    @DrawableRes iconResId: Int,
    onClick: () -> Unit,
) {
    Button(
        onClick = onClick,
        modifier = Modifier.size(148.dp),
        shape = RoundedCornerShape(24.dp),
        border = (BorderStroke(1.dp, Color(0xff707070))),
        colors = ButtonDefaults.buttonColors(backgroundColor = Color.White),
        contentPadding = PaddingValues(24.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(iconResId),
                contentDescription = null,
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = stringResource(textResId),
                textAlign = TextAlign.Center,
            )
        }
    }
}

@Preview
@Composable
fun PreviewHomeConfirmScreen() {
    IconWallPaperTheme {
        Surface {
            HomeConfirmContent(rootNavController = rememberNavController())
        }
    }
}

private fun createImageBitmap(
    density: Density,
    layoutDirection: LayoutDirection,
    onDraw: DrawScope.() -> Unit,
): ImageBitmap {
    // TODO 端末の縦幅と横幅を指定する
    val width = 512
    val height = 512
    val imageBitmap = ImageBitmap(width, height)
    val size = Size(
        width = width.toFloat(),
        height = height.toFloat()
    )

    CanvasDrawScope().draw(
        density,
        layoutDirection,
        androidx.compose.ui.graphics.Canvas(imageBitmap),
        size
    ) {
        onDraw(this)
    }

    return imageBitmap
}

private fun saveImage(
    applicationContext: Context,
    density: Density,
    layoutDirection: LayoutDirection,
    onDraw: DrawScope.() -> Unit,
) {
    val imageBitmap = createImageBitmap(density, layoutDirection, onDraw)
    val manager = MediaStoreManager(applicationContext)
    manager.saveToMediaImages(
        bitmap = imageBitmap.asAndroidBitmap(),
        group = MediaStoreManager.Group.Output,
    )
}

private fun setWallpaper(
    applicationContext: Context,
    localContext: Context,
    density: Density,
    layoutDirection: LayoutDirection,
    target: PlatformSetWallpaperTargetUiModel,
    onDraw: DrawScope.() -> Unit,
    onSuccess: () -> Unit,
    onFailure: () -> Unit,
) {
    val imageBitmap = createImageBitmap(density, layoutDirection, onDraw)
    val manager = WallpaperManagerWrapper(applicationContext)

    when (target) {
        PlatformSetWallpaperTargetUiModel.Home -> {
            manager.setBitmapToSystem(
                bitmap = imageBitmap.asAndroidBitmap(),
                onSuccess = onSuccess,
                onFailure = { onFailure() },
            )
        }
        PlatformSetWallpaperTargetUiModel.Lock -> {
            manager.setBitmapToLock(
                bitmap = imageBitmap.asAndroidBitmap(),
                onSuccess = onSuccess,
                onFailure = { onFailure() },
            )
        }
        PlatformSetWallpaperTargetUiModel.HomeAndLock -> {
            manager.setBitmapToLockAndSystem(
                bitmap = imageBitmap.asAndroidBitmap(),
                onSuccess = onSuccess,
                onFailure = { onFailure() },
            )
        }
        PlatformSetWallpaperTargetUiModel.OtherApp -> openSetWallpaperChooser(
            applicationContext,
            localContext,
            imageBitmap.asAndroidBitmap(),
        )
    }
}

private fun openSetWallpaperChooser(
    applicationContext: Context,
    localContext: Context,
    bitmap: Bitmap,
) {
    val wallpaperManagerWrapper = WallpaperManagerWrapper(applicationContext)
    val mediaStoreManager = MediaStoreManager(localContext)
    val uri = mediaStoreManager.saveToMediaImages(
        bitmap = bitmap,
        group = MediaStoreManager.Group.TmpForSetWallpaperByOtherApp,
    )

    val chooserIntent = wallpaperManagerWrapper.getChooserIntentForSetWallpaper(
        uri = uri,
        type = "image/png",
        stringRes = R.string.home_confirm_set_wallpaper_with_other_app,
    )
    localContext.startActivity(chooserIntent)
}
