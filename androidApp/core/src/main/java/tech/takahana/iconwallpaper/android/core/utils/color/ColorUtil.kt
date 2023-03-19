package tech.takahana.iconwallpaper.android.core.utils.color

import androidx.compose.ui.graphics.Color
import tech.takahana.iconwallpaper.shared.domain.domainobject.ColorType

fun ColorType.toComposeColor(): Color {
    return Color(hex)
}
