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
    primary_accent = color_red,
    accent_content = color_white,
    secondary_accent = color_yellow,
    tertiary_accent = color_green,
    caption = Color.Black.copy(alpha = 0.5f),
    hint = color_gray_500,
    shadow = color_gray_600
)

data class AppColors(
    val backgrounds: Backgrounds,
    val content: Color,
    val primary_accent: Color,
    val accent_content: Color,
    val secondary_accent: Color,
    val tertiary_accent: Color,
    val caption: Color,
    val hint: Color,
    val shadow: Color,
) {
    data class Backgrounds(
        val primary: Color,
        val secondary: Color,
        val tertiary: Color,
    )
}

val color_white = Color(0xffffffff)

val color_black = Color(0xff2E2E30)

val color_gray_100 = Color(0xff979FB2)
val color_gray_300 = Color(0xffF7F7F7)
val color_gray_400 = Color(0xffF1F6FB)
val color_gray_500 = Color(0xffE0E2E8)
val color_gray_600 = Color(0xffE5E5E5)

val color_red = Color(0xffE5001C)
val color_yellow = Color(0xffFFDB00)
val color_green = Color(0xff3CAA6E)

