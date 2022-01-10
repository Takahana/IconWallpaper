package tech.takahana.iconwallpaper.android.onbording.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import tech.takahana.iconwallpaper.android.onbording.R
import tech.takahana.iconwallpaper.android.onbording.ui.components.PrimaryColorButton

@Composable
fun WelcomeScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Bottom,
    ) {
        Image(
            painter = painterResource(id = R.drawable.welcome_dog),
            contentDescription = stringResource(R.string.welcome_dog),
        )
        Spacer(modifier = Modifier.height(24.dp))
        Text(
            text = stringResource(R.string.welcome_message),
            style = MaterialTheme.typography.h5,
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = stringResource(R.string.make_cute_wallpapers),
            style = MaterialTheme.typography.subtitle1,
        )
        Spacer(modifier = Modifier.height(8.dp))
        Box(
            modifier = Modifier
                .padding(20.dp),
            contentAlignment = Alignment.Center
        ) {
            PrimaryColorButton(
                onClick = { /*TODO*/ },
                backgroundColor = MaterialTheme.colors.primary,
                text = stringResource(R.string.make),
            )
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun PreviewWelcomeScreen() {
    WelcomeScreen()
}
