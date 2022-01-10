package tech.takahana.iconwallpaper.usecase.home

import com.futuremind.koru.FlowWrapper
import com.futuremind.koru.ScopeProvider
import com.futuremind.koru.SuspendWrapper
import kotlin.Unit

public class HomeUseCaseImplIos(
  private val wrapped: HomeUseCaseImpl,
  private val scopeProvider: ScopeProvider?
) {
  public val onFinishHomeFlow: FlowWrapper<Unit>
    get() = com.futuremind.koru.FlowWrapper(scopeProvider, false, wrapped.onFinishHomeFlow)

  public constructor(wrapped: HomeUseCaseImpl) : this(wrapped,null)

  public fun finishHome(): SuspendWrapper<Unit> = SuspendWrapper(scopeProvider, false) {
      wrapped.finishHome() }
}
