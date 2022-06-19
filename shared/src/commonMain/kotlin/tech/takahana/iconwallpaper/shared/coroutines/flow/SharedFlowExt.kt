package tech.takahana.iconwallpaper.shared.coroutines.flow

import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow

fun <T> MutableSharedFlowReplayable() = MutableSharedFlow<T>(
    replay = 1,
    extraBufferCapacity = 1,
    onBufferOverflow = BufferOverflow.DROP_OLDEST
)

/**
 * 主にUiLogicからUIにEffectを通知するために使う
 */
fun <T> MutableEffectSharedFlow() = MutableSharedFlow<T>(
    replay = 0,
    extraBufferCapacity = 1,
    onBufferOverflow = BufferOverflow.DROP_OLDEST
)
