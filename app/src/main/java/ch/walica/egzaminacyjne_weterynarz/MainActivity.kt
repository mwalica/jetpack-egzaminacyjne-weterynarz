package ch.walica.egzaminacyjne_weterynarz

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ch.walica.egzaminacyjne_weterynarz.ui.theme.Egzaminacyjne_weterynarzTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Egzaminacyjne_weterynarzTheme {
                MainScreen()
            }
        }
    }
}

@Composable
fun MainScreen() {

    val animals = listOf(
        Animal(name = "Pies", age = 18),
        Animal(name = "Kot", age = 20),
        Animal(name = "Świnka morska", age = 9),
    )

    var fullName by remember {
        mutableStateOf("")
    }

    var selectedAnimal by remember {
        mutableStateOf(animals[1])
    }

    var selectedAge by remember {
        mutableFloatStateOf(0f)
    }

    var goal by remember {
        mutableStateOf("")
    }

    var time by remember {
        mutableStateOf("16:00")
    }

    var result by remember {
        mutableStateOf("")
    }

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Text(
            text = "Wizyta u weterynarza", modifier = Modifier.fillMaxWidth()
        )
        TextField(value = fullName,
            onValueChange = { fullName = it },
            modifier = Modifier.fillMaxWidth(),
            placeholder = {
                Text(
                    text = "Imię i nazwisko właściciela..."
                )
            })
        Text(text = "Gatunek")
        LazyColumn(
            modifier = Modifier.fillMaxWidth()
        ) {
            items(count = animals.size) { index ->
                val animal = animals[index]
                Column(modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        selectedAnimal = animal
                    }) {
                    Text(text = animal.name, modifier = Modifier.padding(vertical = 4.dp))
                    if (index != animals.size - 1) {
                        Divider()
                    }
                }


            }
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = "Ile ma lat? ${(selectedAge * selectedAnimal.age).toInt()}")
            Slider(value = selectedAge, onValueChange = { selectedAge = it })
        }

        TextField(
            value = goal,
            onValueChange = { goal = it },
            placeholder = { Text(text = "Cel wizyty") },
            modifier = Modifier.fillMaxWidth()
        )

        TextField(value = time, onValueChange = { time = it }, modifier = Modifier.fillMaxWidth())
        Button(onClick = {
            result =
                "$fullName, ${selectedAnimal.name}, ${(selectedAge * selectedAnimal.age).toInt()}, $goal, $time"
        }) {
            Text(text = "OK")
        }

        Text(text = result)
    }

}

data class Animal(
    val name: String, val age: Int
)

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Egzaminacyjne_weterynarzTheme {
        MainScreen()
    }
}