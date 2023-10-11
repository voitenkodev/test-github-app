import androidx.compose.foundation.background
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import atomic.appTypography
import atomic.darkPalette
import atomic.lightPalette

@Composable
fun DesignTheme(
    modifier: Modifier = Modifier,
    darkTheme: Boolean = false,
    content: @Composable () -> Unit,
) = CompositionLocalProvider(

    LocalAppColors provides when (darkTheme) {
        true -> darkPalette()
        false -> lightPalette()
    },

    LocalAppTypography provides appTypography(),

    content = {
        Surface(
            modifier = Modifier.background(Design.colors.backgrounds.primary).then(modifier),
            color = Design.colors.backgrounds.primary,
            content = content
        )
    }
)
