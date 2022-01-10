package tech.takahana.iconwallpaper.android.onbording.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun PrimaryColorButton(onClick: () -> Unit, backgroundColor: Color, text: String) {
    TextButton(
        onClick = onClick,
        shape = CircleShape,
        border = (BorderStroke(1.dp, Color.Black)),
        modifier = Modifier
            .background(
                color = backgroundColor,
                shape = CircleShape,
            )
            .height(48.dp)
            .fillMaxWidth(),
    ) {
        Text(
            text = text,
            color = Color.Black,
            style = MaterialTheme.typography.button,
        )
    }
}

@Preview
@Composable
fun PreviewBottomButton() {
    PrimaryColorButton(onClick = { }, backgroundColor = Color(0xffffebee), text = "ボタン")
}
