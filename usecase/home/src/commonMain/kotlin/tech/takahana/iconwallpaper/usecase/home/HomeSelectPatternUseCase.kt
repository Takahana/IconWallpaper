package tech.takahana.iconwallpaper.usecase.home

import kotlinx.coroutines.flow.Flow
import tech.takahana.iconwallpaper.shared.domain.domainobject.BackgroundColor
import tech.takahana.iconwallpaper.shared.domain.domainobject.PatternType

/**
 * ホームパターン選択ページのUseCase(Stateless)
 */
interface HomeSelectPatternUseCase {

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

    /**
     * パターンを選択するユースケース
     */
    suspend fun selectPattern(patternType: PatternType)

    /**
     * 背景色を選択するユースケース
     */
    suspend fun selectBackgroundColor(backgroundColor: BackgroundColor)

    /**
     * タブを切り替えるユースケース
     */
    suspend fun changeTab(target: SwitchTabUseCaseModel): Result<SwitchTabUseCaseModel>
}
