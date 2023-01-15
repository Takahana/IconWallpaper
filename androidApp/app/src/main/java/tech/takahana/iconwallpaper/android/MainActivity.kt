package tech.takahana.iconwallpaper.android

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint
import tech.takahana.iconwallpaper.android.core.ui.theme.IconWallPaperTheme

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            IconWallPaperTheme {
                NavRoot()
            }
        }
    }
}
