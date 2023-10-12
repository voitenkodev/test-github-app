package components.inputs

import Design
import DesignString
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import controls.Input

@Composable
fun InputSearch(
    modifier: Modifier = Modifier,
    value: String,
    loading: Boolean = false,
    onValueChange: (String) -> Unit,
) {
    val focusManager = LocalFocusManager.current

    Input(
        modifier = modifier
            .padding(horizontal = 12.dp, vertical = 6.dp),
        value = value,
        onValueChange = onValueChange,
        trailing = {

            AnimatedVisibility(
                visible = loading,
                enter = fadeIn() + scaleIn(),
                exit = scaleOut() + fadeOut(),
            ) {
                CircularProgressIndicator(
                    modifier = Modifier.size(20.dp)
                )
            }

            AnimatedVisibility(
                visible = value.isNotEmpty() && loading.not(),
                enter = fadeIn() + scaleIn(),
                exit = scaleOut() + fadeOut(),
            ) {
                IconButton(
                    onClick = { onValueChange.invoke("") },
                ) {
                    Icon(
                        imageVector = Icons.Default.Clear,
                        tint = Design.colors.caption,
                        contentDescription = "Icon for clear search field"
                    )
                }
            }
        },
        leading = {
            Icon(
                imageVector = Icons.Default.Search,
                tint = Design.colors.caption,
                contentDescription = "Icon for search"
            )
        },
        maxLines = 1,
        keyboardActions = KeyboardActions { focusManager.moveFocus(FocusDirection.Next) },
        keyboardOptions = KeyboardOptions.Default,
        placeholder = stringResource(id = DesignString.ph_search),
        textStyle = Design.typography.BODY1,

        )
}