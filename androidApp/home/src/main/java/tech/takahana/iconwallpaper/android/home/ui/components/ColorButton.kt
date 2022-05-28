package tech.takahana.iconwallpaper.android.home.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import tech.takahana.iconwallpaper.android.home.R
import tech.takahana.iconwallpaper.shared.domain.domainobject.ColorType

@Composable
fun ColorButton(
    color: ColorType,
    currentColor: ColorType,
    onClick: (ColorType) -> Unit
) {
    Box(
        modifier = Modifier
            .clip(CircleShape)
            .clickable { onClick(color) }
            .size(40.dp)
            .background(Color(color.hex))
    ) {
        if (color == currentColor) {
            Image(
                painter = painterResource(R.drawable.ic_check_circle_24),
                contentDescription = null,
                modifier = Modifier.padding(8.dp)
            )
        }
    }
}
