package tech.takahana.iconwallpaper.usecase.home

import kotlinx.coroutines.flow.Flow
import tech.takahana.iconwallpaper.shared.domain.domainobject.PatternType

/**
 * ホームパターン選択ページのUseCase(Stateless)
 */
interface HomeSelectPatternUseCase {

    /**
     * 選択済みの素材のFlow
     * 更新されたら新しいデータが放出される
     */
    val selectedPatternFlow: Flow<SelectedPatternUseCaseModel>

    /**
     * パターンを選択するユースケース
     */
    suspend fun selectPattern(patternType: PatternType)
}
