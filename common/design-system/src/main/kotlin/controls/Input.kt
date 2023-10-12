package controls

import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.VisualTransformation

@Composable
fun Input(
    modifier: Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
    enabled: Boolean = true,
    textStyle: TextStyle,
    maxLines: Int,
    digits: Array<Char> = emptyArray(),
    visualTransformation: VisualTransformation = VisualTransformation.None,
    maxLength: Int = Int.MAX_VALUE,
    keyboardOptions: KeyboardOptions?,
    keyboardActions: KeyboardActions?,
    leading: @Composable (() -> Unit)? = null,
    trailing: @Composable (() -> Unit)? = null,
) {

    val updater by remember {
        mutableStateOf(
            { s: String ->
                val v = s.take(maxLength)
                val digitsFilter = if (digits.isNotEmpty()) v.filter { char -> digits.contains(char) } else v
                onValueChange.invoke(digitsFilter)
            }
        )
    }

    TextField(
        modifier = modifier,
        value = value,
        onValueChange = updater,
        enabled = enabled,
        textStyle = textStyle,
        leadingIcon = leading,
        trailingIcon = trailing,
        label = {
            Text(
                text = placeholder,
            )
        },
        maxLines = maxLines,
        visualTransformation = visualTransformation,
        singleLine = maxLines == 1,
        keyboardOptions = keyboardOptions ?: KeyboardOptions.Default,
        keyboardActions = keyboardActions ?: KeyboardActions.Default,
    )
}