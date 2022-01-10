package tech.takahana.iconwallpaper

import tech.takahana.iconwallpapaer.uilogic.welcome.WelcomeUiLogicImpl
import tech.takahana.iconwallpapaer.uilogic.welcome.WelcomeUiLogicImplIos
import tech.takahana.iconwallpapaer.uilogic.welcome.WelcomeUiLogicIosProtocol
import tech.takahana.iconwallpaper.usecase.onboarding.WelcomeUseCase
import tech.takahana.iconwallpaper.usecase.onboarding.WelcomeUseCaseImpl

fun createWelcomeUiLogic(): WelcomeUiLogicIosProtocol {
    val useCase: WelcomeUseCase = WelcomeUseCaseImpl()
    return WelcomeUiLogicImplIos(
        wrapped = WelcomeUiLogicImpl(useCase)
    )
}