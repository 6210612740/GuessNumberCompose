package com.example.guessnumbercompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.guessnumbercompose.ui.theme.GuessNumberComposeTheme
import java.util.*

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Main()
        }
    }
}


fun randomNumber(): Int {
    val random = Random()
    return random.nextInt(1000)
}

@Composable
fun Main() {

    var guess = rememberSaveable { mutableStateOf("") }
    var rNum by remember { mutableStateOf(randomNumber()) }
    val count = remember { mutableStateOf(0) }
    val textlabel = remember { mutableStateOf("") }
    Column(
        modifier = Modifier.fillMaxHeight(),
        verticalArrangement = Arrangement.SpaceBetween,

        ) {
        Text(
            text = "Guess Number Between 1 to 1000",
            fontSize = 20.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .padding(horizontal = 20.dp)
                .fillMaxWidth()
        )

        Text(
            text = textlabel.value,
            textAlign = TextAlign.Center,
            fontSize = 20.sp,
            modifier = Modifier
                .padding()
                .fillMaxWidth()
        )


        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            //Reset Button
            Button(
                onClick = {
                    count.value = 0
                    textlabel.value = ""
                    rNum = randomNumber()
                }) {
                Text(
                    text = "Reset", fontSize =  10.sp, modifier = Modifier
                        .padding()

                )
            }

            TextField(
                value = guess.value,
                onValueChange = { guess.value = it },
                label = { Text("Number") },
                modifier = Modifier
                    .padding()
            )

            //Guess Button
            Button(
                onClick = {
                    var number = guess.value.toInt()
                    if (number < rNum) {
                        count.value += 1
                        textlabel.value = "Your Guess is too low"
                    } else if (number > rNum) {
                        count.value += 1
                        textlabel.value = "Your Guess is too high"
                    } else {
                        count.value += 1
                        textlabel.value = "YOU HAVE THE RIGHT GUESS!"

                    }

                }) {
                Text(text = "Guess", fontSize =  10.sp, modifier = Modifier
                    .padding())
            }
        }


        Text(
            text = "Times: " + count.value,
            fontSize = 20.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)

        )
    }

}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    Main()
}