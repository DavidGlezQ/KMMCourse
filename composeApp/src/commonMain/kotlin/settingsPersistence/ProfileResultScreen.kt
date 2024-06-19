package settingsPersistence

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.russhwolf.settings.Settings
import settingsPersistence.ProfileScreen.Companion.KEY_NAME
import settingsPersistence.ProfileScreen.Companion.KEY_VIP

class ProfileResultScreen : Screen {
    private val settings: Settings = Settings()

    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        val isVip = settings.getBoolean(KEY_VIP, false)
        val backGroundColor = if (isVip) Color.Yellow else Color.White

        Column(
            modifier = Modifier.fillMaxSize().background(backGroundColor),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            val name = settings.getString(KEY_NAME, "")
            Text("Welcome, $name", fontSize = 26.sp, fontWeight = FontWeight.Bold)
            Button(onClick = {
                //settings.remove(KEY_VIP)
                settings.clear()
                navigator.pop()
            }) {
                Text("Delete data and back screen")
            }
        }
    }

}