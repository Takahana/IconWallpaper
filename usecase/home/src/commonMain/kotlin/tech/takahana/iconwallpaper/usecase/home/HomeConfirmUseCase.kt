package tech.takahana.iconwallpaper.usecase.home

import kotlinx.coroutines.flow.Flow

interface HomeConfirmUseCase {

    /**
     * 選択済みの素材のFlow
     * 更新されたら新しいデータが放出される
     */
    val selectedImageAssetFlow: Flow<ImageAssetUseCaseModel>

    /**
     * 選択済みのパターンのFlow
     */
    val selectedPatternFlow: Flow<SelectedPatternUseCaseModel>

    /**
     * 選択済みの背景色のFlow
     */
    val selectedBackgroundColorFlow: Flow<SelectedBackgroundColorUseCaseModel>

    fun saveWallpaper(): Result<Unit>

    fun setWallpaper(): Result<Unit>

    fun cancelSetWallpaper(): Result<Unit>

    fun selectSetWallpaperTarget(target: SetWallpaperTargetUseCaseModel): Result<SetWallpaperTargetUseCaseModel>
}
