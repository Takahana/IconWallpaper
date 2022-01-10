package tech.takahana.iconwallpaper.usecase.onboarding

import com.futuremind.koru.FlowWrapper
import com.futuremind.koru.SuspendWrapper
import kotlin.Unit

public interface HomeUseCaseIosProtocol {
  public val onFinishHomeFlow: FlowWrapper<Unit>

  public fun finishHome(): SuspendWrapper<Unit>
}
