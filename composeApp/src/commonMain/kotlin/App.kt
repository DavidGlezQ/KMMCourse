import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import io.ktor.client.request.get
import io.ktor.client.statement.bodyAsText
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.launch
import network.NetworkUtils.httpClient
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    MaterialTheme {
        val url = "https://www.superheroapi.com/api.php/cfc55c379d941f22661c7167750d91b9/search/a"
        var response by remember { mutableStateOf("Waiting...") }
        Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
            Button(onClick = {
                response = "Loading..."
                CoroutineScope(Dispatchers.IO).launch {
                    val testResponse = httpClient.get(url)
                    testResponse.bodyAsText()
                }
            }) {
                Text("Call SuperHero API!")
            }
            Text(response)
        }
    }
}

/** superhero API
 * endpoint name example
 * https://www.superheroapi.com/api.php/cfc55c379d941f22661c7167750d91b9/search/a
 * cfc55c379d941f22661c7167750d91b9
 * */