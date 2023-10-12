package atomic

import androidx.compose.ui.graphics.Color

fun lightPalette() = darkPalette()

fun darkPalette() = AppColors(
    AppColors.Backgrounds(
        primary = color_white,
        secondary = color_gray_300,
        tertiary = color_gray_400,
    ),
    content = color_black,
    caption = Color.Black.copy(alpha = 0.5f),
    shadow = color_gray_600,

    primary_accent = color_red,
    secondary_accent = color_yellow,
)

data class AppColors(
    val backgrounds: Backgrounds,
    val content: Color,
    val caption: Color,
    val shadow: Color,
    val primary_accent: Color,
    val secondary_accent: Color,
) {
    data class Backgrounds(
        val primary: Color,
        val secondary: Color,
        val tertiary: Color,
    )
}

val color_white = Color(0xffffffff)
val color_black = Color(0xff2E2E30)
val color_gray_300 = Color(0xffF7F7F7)
val color_gray_400 = Color(0xffF1F6FB)
val color_gray_600 = Color(0xffE5E5E5)
val color_red = Color(0xffE5001C)
val color_yellow = Color(0xffFFDB00)