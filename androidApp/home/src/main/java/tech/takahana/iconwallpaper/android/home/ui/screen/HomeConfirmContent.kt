package tech.takahana.iconwallpaper.android.home.ui.screen

import android.Manifest
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asAndroidBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.drawscope.CanvasDrawScope
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.imageResource
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
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import com.google.accompanist.permissions.shouldShowRationale
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.drop
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import tech.takahana.iconwallpaper.android.core.Screen
import tech.takahana.iconwallpaper.android.core.ui.theme.IconWallPaperTheme
import tech.takahana.iconwallpaper.android.core.utils.MediaStoreManager
import tech.takahana.iconwallpaper.android.core.utils.wallpaper.WallpaperManagerWrapper
import tech.takahana.iconwallpaper.android.home.R
import tech.takahana.iconwallpaper.android.home.ui.components.StepAnnouncement
import tech.takahana.iconwallpaper.android.home.ui.screen.viewmodel.HomeConfirmViewModel
import tech.takahana.iconwallpaper.android.home.ui.util.DrawScopeUtils.drawPattern
import tech.takahana.iconwallpaper.shared.assets.BitmapImageAsset
import tech.takahana.iconwallpaper.shared.assets.LocalImageAsset
import tech.takahana.iconwallpaper.shared.domain.domainobject.ColorType
import tech.takahana.iconwallpaper.shared.domain.domainobject.ImageAsset
import tech.takahana.iconwallpaper.shared.domain.domainobject.PatternType
import tech.takahana.iconwallpaper.uilogic.home.HomeConfirmUiLogic
import tech.takahana.iconwallpaper.uilogic.home.ImageAssetUiModel
import tech.takahana.iconwallpaper.uilogic.home.PlatformSetWallpaperTargetUiModel
import tech.takahana.iconwallpaper.uilogic.home.SetWallpaperTargetUiModel

@OptIn(ExperimentalPermissionsApi::class)
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
    val localConfiguration = LocalConfiguration.current
    val screenWidthDp = localConfiguration.screenWidthDp.dp
    val screenHeightDp = localConfiguration.screenHeightDp.dp
    val (screenWidthPx, screenHeightPx) = with(LocalDensity.current) {
        screenWidthDp.toPx() to screenHeightDp.toPx()
    }

    val patternType by uiLogic.patternTypeStateFlow.collectAsState()
    val backgroundColor by uiLogic.backgroundColorStateFlow.collectAsState()
    val selectedImageAsset by uiLogic.selectedImageAssetStateFlow.collectAsState()
    val openSetWallpaperDialog: Boolean by uiLogic.openSetWallpaperTargetDialogStateFlow.collectAsState()
    val openConfirmPermissionDialog: Boolean by uiLogic.openPermissionRequestRationaleDialogStateFlow.collectAsState()
    val permissionState =
        rememberPermissionState(permission = Manifest.permission.WRITE_EXTERNAL_STORAGE)
    var rememberedOnDraw by remember { mutableStateOf<DrawScope.() -> Unit>({}) }

    LaunchedEffect(Unit) {
        snapshotFlow { permissionState.status }.drop(1).onEach {
            uiLogic.onPermissionStateChanged(it.isGranted)
        }.launchIn(this)

        uiLogic.saveWallpaperEffect.onEach {
            rootNavController.navigate(Screen.WelcomeScreen.route)
            saveImage(
                width = screenWidthPx.toInt(),
                height = screenHeightPx.toInt(),
                applicationContext,
                density,
                layoutDirection,
                rememberedOnDraw,
            )
            // rememberedOnDrawのラムダ内で参照しているものがリークしないように、空のラムダをセットしておく。
            rememberedOnDraw = {}
        }.launchIn(this)

        uiLogic.permissionRequestEffect.onEach {
            permissionState.launchPermissionRequest()
        }.launchIn(this)
    }

    when (selectedImageAsset) {
        ImageAssetUiModel.None -> {
            // TODO エラー表示
        }

        is ImageAssetUiModel.AssetSelectable -> {
            // スマートキャストを聞かせるためにasを利用する
            val imageAsset = selectedImageAsset as ImageAssetUiModel.AssetSelectable

            ImageAssetConfirmContent(
                modifier = modifier,
                screenWidthPx = screenWidthPx,
                screenHeightPx = screenHeightPx,
                patternType = patternType,
                backgroundColor = backgroundColor,
                imageAsset = imageAsset.imageAsset,
                setWallpaperEffect = uiLogic.setWallpaperEffect,
                uiLogic = uiLogic,
                onClickedSaveWallpaper = { onDraw ->
                    rememberedOnDraw = onDraw
                    uiLogic.onClickedSaveWallpaper(
                        canSkipPermissionRequest = MediaStoreManager.canSkipPermissionRequest,
                        isPermissionRequestGrant = permissionState.status.isGranted,
                        shouldShowPermissionRequestRationale = permissionState.status.shouldShowRationale,
                    )
                },
                onClickedSetWallpaper = {
                    uiLogic.onClickedSetWallpaper()
                }
            )
        }
    }

    if (openSetWallpaperDialog) {
        HomeConfirmSetWallpaperDialog(uiLogic)
    }

    if (openConfirmPermissionDialog) {
        HomeConfirmPermissionRequestRationaleDialog(uiLogic)
    }
}

@Composable
private fun ImageAssetConfirmContent(
    modifier: Modifier = Modifier,
    screenWidthPx: Float,
    screenHeightPx: Float,
    patternType: PatternType,
    backgroundColor: ColorType,
    imageAsset: ImageAsset,
    setWallpaperEffect: SharedFlow<SetWallpaperTargetUiModel>,
    uiLogic: HomeConfirmUiLogic,
    onClickedSaveWallpaper: (onDraw: DrawScope.() -> Unit) -> Unit,
    onClickedSetWallpaper: () -> Unit,
) {
    val density = LocalDensity.current
    val layoutDirection = LocalLayoutDirection.current
    val applicationContext = requireNotNull(LocalContext.current.applicationContext)
    val localContext = LocalContext.current

    val imageBitmap = when (imageAsset) {
        is LocalImageAsset -> ImageBitmap.imageResource(id = imageAsset.resId)
        is BitmapImageAsset -> imageAsset.bitmap.asImageBitmap()
        else -> Bitmap.createBitmap(100, 100, Bitmap.Config.ARGB_8888).asImageBitmap()
    }

    val onDraw: DrawScope.() -> Unit = {
        drawPattern(
            image = imageBitmap,
            backgroundColor = Color(backgroundColor.hex),
            drawNum = patternType.drawNum
        )
    }

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
                onClick = { onClickedSaveWallpaper(onDraw) },
            )
            Spacer(modifier = Modifier.width(40.dp))
            ActionButton(
                textResId = R.string.home_confirm_set_wallpaper,
                iconResId = R.drawable.ic_wallpaper_24,
                onClick = onClickedSetWallpaper,
            )
        }
    }

    LaunchedEffect(key1 = Unit) {
        setWallpaperEffect.onEach { target ->
            setWallpaper(
                width = screenWidthPx.toInt(),
                height = screenHeightPx.toInt(),
                applicationContext = applicationContext,
                localContext = localContext,
                density = density,
                layoutDirection = layoutDirection,
                target = target as PlatformSetWallpaperTargetUiModel,
                onDraw = onDraw,
                onSuccess = {
                    uiLogic.onSuccessSetWallPaper()
                },
                onFailure = {
                    // TODO メッセージを表示
                },
            )
        }.launchIn(this)
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
fun PreviewHomeConfirmContent() {
    IconWallPaperTheme {
        Surface {
            HomeConfirmContent(rootNavController = rememberNavController())
        }
    }
}

private fun createImageBitmap(
    width: Int,
    height: Int,
    density: Density,
    layoutDirection: LayoutDirection,
    onDraw: DrawScope.() -> Unit,
): ImageBitmap {
    val imageBitmap = ImageBitmap(width, height)
    val size = Size(
        width = width.toFloat(), height = height.toFloat()
    )

    CanvasDrawScope().draw(
        density, layoutDirection, androidx.compose.ui.graphics.Canvas(imageBitmap), size
    ) {
        onDraw(this)
    }

    return imageBitmap
}

private fun saveImage(
    width: Int,
    height: Int,
    applicationContext: Context,
    density: Density,
    layoutDirection: LayoutDirection,
    onDraw: DrawScope.() -> Unit,
) {
    val imageBitmap = createImageBitmap(width, height, density, layoutDirection, onDraw)
    val manager = MediaStoreManager(applicationContext)
    manager.saveToMediaImages(
        bitmap = imageBitmap.asAndroidBitmap(),
        group = MediaStoreManager.Group.Output,
    )
}

private fun setWallpaper(
    width: Int,
    height: Int,
    applicationContext: Context,
    localContext: Context,
    density: Density,
    layoutDirection: LayoutDirection,
    target: PlatformSetWallpaperTargetUiModel,
    onDraw: DrawScope.() -> Unit,
    onSuccess: () -> Unit,
    onFailure: () -> Unit,
) {
    val imageBitmap = createImageBitmap(width, height, density, layoutDirection, onDraw)
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
