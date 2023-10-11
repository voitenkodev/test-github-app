package atomic

import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

fun appTypography() = AppTypography(
    Title32 = TextStyle(
        fontSize = 32.sp,
        fontWeight = FontWeight.SemiBold,
        fontStyle = FontStyle.Normal,
    ),
    Title48 = TextStyle(
        fontSize = 48.sp,
        fontWeight = FontWeight.Bold,
        fontStyle = FontStyle.Normal,
    ),
    H1 = TextStyle(
        fontSize = 57.sp,
        fontWeight = FontWeight.Bold,
        fontStyle = FontStyle.Normal,
        lineHeight = 40.sp
    ),
    H2 = TextStyle(
        fontSize = 30.sp,
        fontWeight = FontWeight.Bold,
        fontStyle = FontStyle.Normal,
        lineHeight = 40.sp
    ),
    H3 = TextStyle(
        fontSize = 24.sp,
        fontWeight = FontWeight.Bold,
        fontStyle = FontStyle.Normal,
        lineHeight = 30.sp
    ),
    H5 = TextStyle(
        fontSize = 20.sp,
        fontWeight = FontWeight.Bold,
        fontStyle = FontStyle.Normal,
        lineHeight = 24.sp
    ),
    H8 = TextStyle(
        fontSize = 16.sp,
        fontWeight = FontWeight.Medium,
        fontStyle = FontStyle.Normal,
        lineHeight = 21.sp
    ),
    H10 = TextStyle(
        fontSize = 14.sp,
        fontWeight = FontWeight.Bold,
        fontStyle = FontStyle.Normal,
        lineHeight = 18.sp
    ),
    BODY1 = TextStyle(
        fontSize = 14.sp,
        fontWeight = FontWeight.Medium,
        fontStyle = FontStyle.Normal,
        lineHeight = 17.sp
    ),
    BODY2 = TextStyle(
        fontSize = 16.sp,
        fontWeight = FontWeight.Normal,
        fontStyle = FontStyle.Normal,
        lineHeight = 24.sp
    ),
    BODY6 = TextStyle(
        fontSize = 12.sp,
        fontWeight = FontWeight.Normal,
        fontStyle = FontStyle.Normal,
        lineHeight = 14.sp
    ),
    BODY9 = TextStyle(
        fontSize = 10.sp,
        fontWeight = FontWeight.Normal,
        fontStyle = FontStyle.Normal,
        lineHeight = 10.sp
    ),
    TOOLBAR_TITLE = TextStyle(
        fontSize = 18.sp,
        fontWeight = FontWeight.Bold,
        fontStyle = FontStyle.Normal,
        lineHeight = 16.sp
    ),
    AVATAR = TextStyle(
        fontSize = 57.sp,
        fontWeight = FontWeight.Bold,
        fontStyle = FontStyle.Normal,
        lineHeight = 16.sp
    ),
    MENU = TextStyle(
        fontSize = 12.sp,
        fontWeight = FontWeight.Medium,
        fontStyle = FontStyle.Normal,
        lineHeight = 24.sp
    )
)

data class AppTypography(
    val Title48: TextStyle,
    val Title32: TextStyle,


    val H1: TextStyle,
    val H2: TextStyle, // [Main big title, ]
    val H3: TextStyle, // [Price Title, ]
    val H5: TextStyle, // [Ingredient Price, ]
    val H8: TextStyle, // [Screen SUB titles, Item Main menu title, Item Pizza size title, ]
    val H10: TextStyle, // [Item sticky menu title, ]

    val BODY1: TextStyle, // [Secondary Chip, ]
    val BODY2: TextStyle, // [Text Input Field, ]
    val BODY6: TextStyle, // [Ingredient description, ]
    val BODY9: TextStyle, // [Ingredient description, ]

    val TOOLBAR_TITLE: TextStyle, // [Toolbar, ]
    val AVATAR: TextStyle, // [Avatar ]
    val MENU: TextStyle,
)