package tech.takahana.iconwallpaper.repository.asset

import kotlinx.coroutines.flow.Flow
import tech.takahana.iconwallpaper.shared.domain.domainobject.PatternType

/**
 * 選択したパターンのRepository
 */
interface SelectPatternTypeRepository {

    /**
     * @return 選択済みの画像素材のFlow
     */
    val selectedPatternTypeFlow: Flow<PatternType>

    /**
     * 画像素材を選択済みとしてセットする
     *
     * @param patternType 設定するパターン
     */
    suspend fun setSelectedPatternType(patternType: PatternType)
}
