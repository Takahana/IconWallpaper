package tech.takahana.iconwallpaper.repository.asset

import kotlinx.coroutines.flow.Flow
import tech.takahana.iconwallpaper.shared.domain.domainobject.BackgroundColor

/**
 * 選択した背景色のRepository
 */
interface SelectBackgroundColorRepository {

    /**
     * @return 選択済みの画像背景色のFlow
     */
    val selectBackgroundColorFlow: Flow<BackgroundColor>

    /**
     * 画像素材を選択済みとしてセットする
     *
     * @param backgroundColor 設定する背景色
     */
    suspend fun setSelectedBackgroundColor(backgroundColor: BackgroundColor)
}
