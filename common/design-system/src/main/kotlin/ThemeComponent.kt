import androidx.compose.runtime.Composable
import androidx.compose.runtime.staticCompositionLocalOf
import atomic.AppColors
import atomic.AppTypography

object Design {

    val colors: AppColors
        @Composable
        get() = LocalAppColors.current

    val typography: AppTypography
        @Composable
        get() = LocalAppTypography.current

}

internal val LocalAppColors = staticCompositionLocalOf<AppColors> { error("No AppColors provided") }

internal val LocalAppTypography = staticCompositionLocalOf<AppTypography> { error("No AppTypography provided") }