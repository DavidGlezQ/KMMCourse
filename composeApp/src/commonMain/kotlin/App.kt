import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import androidx.compose.ui.unit.dp
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.statement.bodyAsText
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.launch
import network.NetworkUtils.httpClient
import network.model.ApiResponse
import network.model.Hero
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    MaterialTheme {
        //var response by remember { mutableStateOf("Waiting...") }
        var superHeroName by remember { mutableStateOf("") }
        var superheroList by remember { mutableStateOf<List<Hero>>(emptyList()) }
        Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
            Row(
                modifier = Modifier.fillMaxWidth().padding(12.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                OutlinedTextField(value = superHeroName, onValueChange = { superHeroName = it })
                Button(onClick = { getSuperHeroList(superHeroName) { superheroList = it } }) {
                    Text("Load")
                }
            }
            LazyColumn {
                items(superheroList) { hero ->
                    Text(modifier = Modifier.fillMaxWidth(), text = hero.name)
                }
            }
            /*Button(onClick = {
                response = "Loading..."
                CoroutineScope(Dispatchers.IO).launch {
                    val testResponse = httpClient.get(url)
                    response = testResponse.bodyAsText()
                }
            }) {
                Text("Call SuperHero API!")
            }*/
            //Text(response)
        }
    }
}

fun getSuperHeroList(superHeroName: String, onSuccessResponse: (List<Hero>) -> Unit) {
    if (superHeroName.isBlank()) return
    val url =
        "https://www.superheroapi.com/api.php/cfc55c379d941f22661c7167750d91b9/search/$superHeroName"
    CoroutineScope(Dispatchers.IO).launch {
        val response = httpClient.get(url).body<ApiResponse>()
        onSuccessResponse(response.results)
    }
}

/** superhero API
 * endpoint name example
 * https://www.superheroapi.com/api.php/cfc55c379d941f22661c7167750d91b9/search/a
 * cfc55c379d941f22661c7167750d91b9
 * */