package settingsPersistence

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.russhwolf.settings.Settings

class ProfileScreen : Screen {

    private val settings: Settings = Settings()
    companion object {
        const val KEY_NAME = "NAME"
        const val KEY_VIP = "VIP"
    }

    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        var text by remember { mutableStateOf("") }
        var isVip by remember { mutableStateOf(false) }

        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.weight(1f))
            OutlinedTextField(value = text, onValueChange = { text = it })
            Row(verticalAlignment = Alignment.CenterVertically) {
                Checkbox(checked = isVip, onCheckedChange = { isVip = it })
                Text("Are you VIP?")

            }
            Spacer(modifier = Modifier.weight(1f))
            Button(onClick = {
                settings.putString(KEY_NAME, text)
                settings.putBoolean(KEY_VIP, isVip)
                navigator.push(ProfileResultScreen())
            }) {
                Text("Save Profile")
            }
            Spacer(modifier = Modifier.weight(0.3f))
        }
    }
}