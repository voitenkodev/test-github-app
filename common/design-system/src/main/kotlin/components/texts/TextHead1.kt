package components.texts

import Design
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration

@Composable
fun TextHead1(
    modifier: Modifier = Modifier,
    text: String,
    fontWeight: FontWeight? = null,
    color: Color? = null,
    textAlign: TextAlign? = null,
    textDecoration: TextDecoration? = null,
) {
    Text(
        modifier = modifier,
        text = text,
        style = Design.typography.H1,
        fontWeight = fontWeight,
        color = color ?: Design.colors.content,
        textAlign = textAlign,
        textDecoration = textDecoration
    )
}