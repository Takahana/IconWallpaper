package tech.takahana.iconwallpaper.android

import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import tech.takahana.iconwallpapaer.uilogic.welcome.WelcomeUiLogicImpl
import tech.takahana.iconwallpaper.Greeting
import tech.takahana.iconwallpaper.android.core.ui.theme.IconWallPaperTheme
import tech.takahana.iconwallpaper.android.home.ui.screen.HomeScreen
import tech.takahana.iconwallpaper.uilogic.welcome.WelcomeUiLogic
import tech.takahana.iconwallpaper.usecase.onboarding.WelcomeUseCaseImpl

fun greet(): String {
    return Greeting().greeting()
}

fun welcomeUiLogic(): WelcomeUiLogic {
    return WelcomeUiLogicImpl(
        useCase = WelcomeUseCaseImpl()
    )
}

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val welcomeUiLogic: WelcomeUiLogic by lazy { welcomeUiLogic() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val button: Button = findViewById(R.id.button)
        button.also {
            it.text = greet()
            it.setOnClickListener {
                welcomeOnClickButton()
            }
        }

        subscribeFinishOnBoardingEffect()
        setContent {
            IconWallPaperTheme {
                HomeScreen()
            }
        }
    }

    private fun welcomeOnClickButton() {
        lifecycleScope.launchWhenStarted {
            welcomeUiLogic.onClickedFinishButton()
        }
    }

    private fun subscribeFinishOnBoardingEffect() {
        lifecycleScope.launchWhenCreated {
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                welcomeUiLogic.finishedOnBoardingEffect
                    .onEach {
                        showMessageFinishedOnBoarding()
                    }
                    .launchIn(this)
            }
        }
    }

    private fun showMessageFinishedOnBoarding() {
        Toast.makeText(this, "Onboarding is finished.", Toast.LENGTH_SHORT).show()
    }

    private fun showMessageFinishedHome() {
        Toast.makeText(this, "Home is finished.", Toast.LENGTH_SHORT).show()
    }
}
