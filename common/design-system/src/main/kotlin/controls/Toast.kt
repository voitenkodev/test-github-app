package controls

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext

@Composable
fun Toast(message: String, clear: (() -> Unit)? = null) {
    android.widget.Toast.makeText(LocalContext.current, message, android.widget.Toast.LENGTH_SHORT).show()

    LaunchedEffect(key1 = message) {
        clear?.invoke()
    }
}