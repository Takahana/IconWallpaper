package tech.takahana.iconwallpaper.android

import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import tech.takahana.iconwallpapaer.uilogic.welcome.WelcomeUiLogicImpl
import tech.takahana.iconwallpaper.Greeting
import tech.takahana.iconwallpaper.android.onbording.ui.WelcomeScreen
import tech.takahana.iconwallpaper.uilogic.welcome.WelcomeUiLogic
import tech.takahana.iconwallpaper.usecase.onboarding.WelcomeUseCaseImpl

fun greet(): String {
    return Greeting().greeting()
}

fun uiLogic(): WelcomeUiLogic {
    return WelcomeUiLogicImpl(
        useCase = WelcomeUseCaseImpl()
    )
}

class MainActivity : AppCompatActivity() {

    private val uiLogic: WelcomeUiLogic by lazy { uiLogic() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val button: Button = findViewById(R.id.button)
        button.also {
            it.text = greet()
            it.setOnClickListener {
                onClickButton()
            }
        }

        subscribeFinishOnBoardingEffect()
        setContent {
            WelcomeScreen()
        }
    }

    private fun onClickButton() {
        lifecycleScope.launchWhenStarted {
            uiLogic.onClickedFinishButton()
        }
    }

    private fun subscribeFinishOnBoardingEffect() {
        lifecycleScope.launchWhenCreated {
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                uiLogic.finishedOnBoardingEffect
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
}
