import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import bottomBar.BottomBarScreen
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.currentOrThrow
import cafe.adriel.voyager.transitions.FadeTransition
import cafe.adriel.voyager.transitions.ScaleTransition
import cafe.adriel.voyager.transitions.SlideTransition
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    MaterialTheme {
        Navigator(screen = MainScreen()) { navigator ->
            SlideTransition(navigator)
            //FadeTransition(navigator)
            //ScaleTransition(navigator)
        }
    }
}

class MainScreen : Screen {
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        Column (
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center
        ) {
            Button(modifier = Modifier, onClick = { navigator.push(SecondScreen()) }) {
                Text("Navigate to Second Screen")
            }
            Spacer(modifier = Modifier.height(16.dp))
            Button(modifier = Modifier, onClick = { navigator.push(BottomBarScreen()) }) {
                Text("BottomBar")
            }
        }
    }
}

class SecondScreen : Screen {
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        Column (
            modifier = Modifier.fillMaxSize().background(Color.LightGray),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Second Screen", fontSize = 26.sp, color = Color.White)
            Spacer(modifier = Modifier.height(16.dp))
            Button(modifier = Modifier, onClick = { navigator.pop() }) {
                Text("Back")
            }
        }
    }

}