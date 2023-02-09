package com.example.tictacto

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tictacto.ui.theme.TicTacToTheme


class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TicTacToTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background
                ) {
                    TicTacTo(modifier = Modifier.padding(8.dp))

                }
            }
        }
    }


}


@Composable
fun TicTacTo(modifier: Modifier = Modifier) {
    val decisionMap = remember {
        mutableStateOf(HashMap<String, String>())
    }
    var initialValue by remember {
        mutableStateOf("")
    }
    var crossOrZero by remember {
        mutableStateOf("")
    }
    var isWin by remember {
        mutableStateOf(false)
    }
    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        if (initialValue.isNotEmpty()) {
            GameRow(0, decisionMap.value) { key, value ->
                val updatedMap = HashMap<String, String>().apply {
                    putAll(decisionMap.value)
                    crossOrZero = if (crossOrZero.isEmpty()) {
                        put(key, initialValue)
                        if (initialValue == "O") "X" else "O"
                    } else {
                        put(key, crossOrZero)
                        if (crossOrZero == "O") "X" else "O"
                    }

                }
                decisionMap.value = updatedMap
                isWin = gameWinDecision(
                    key, if (crossOrZero == "O") "X" else "O", decisionMap.value

                )

            }
            Spacer(
                modifier = Modifier
                    .height(2.dp)
                    .background(Color.White)
            )
            GameRow(1, decisionMap.value) { key, value ->
                val updatedMap = HashMap<String, String>().apply {
                    putAll(decisionMap.value)
                    crossOrZero = if (crossOrZero.isEmpty()) {
                        put(key, initialValue)
                        if (initialValue == "O") "X" else "O"
                    } else {
                        put(key, crossOrZero)
                        if (crossOrZero == "O") "X" else "O"
                    }

                }
                decisionMap.value = updatedMap
                isWin = gameWinDecision(
                    key, if (crossOrZero == "O") "X" else "O", decisionMap.value
                )

            }
            Spacer(
                modifier = Modifier
                    .height(2.dp)
                    .background(Color.White)
            )
            GameRow(2, decisionMap.value) { key, value ->
                val updatedMap = HashMap<String, String>().apply {
                    putAll(decisionMap.value)
                    crossOrZero = if (crossOrZero.isEmpty()) {
                        put(key, initialValue)
                        if (initialValue == "O") "X" else "O"
                    } else {
                        put(key, crossOrZero)
                        if (crossOrZero == "O") "X" else "O"
                    }

                }
                decisionMap.value = updatedMap
                isWin = gameWinDecision(
                    key, if (crossOrZero == "O") "X" else "O", decisionMap.value
                )
            }
        }

        if (initialValue.isEmpty()) {
            Text(
                modifier = Modifier.padding(horizontal = 10.dp),
                text = "Welcome Into TicTacTo Game Please Select Your First Move",
                fontSize = 30.sp,
                textAlign = TextAlign.Center
            )


            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Button(
                    onClick = {
                        initialValue = "X"
                    },
                    modifier = Modifier
                        .height(70.dp)
                        .weight(1f)
                        .padding(4.dp)
                ) {

                    Text(text = "Starts With X", fontSize = 20.sp)
                }
                Button(
                    onClick = {
                        initialValue = "O"

                    },
                    modifier = Modifier
                        .height(70.dp)
                        .weight(1f)
                        .padding(4.dp)
                ) {
                    Text(text = "Starts With O", fontSize = 20.sp)
                }
            }

        }
        ShowDailog(
            title = "You Win", message = if (crossOrZero == "X") "O" else "X", isShowing = isWin
        ) {
            isWin = false
            initialValue = ""
            crossOrZero = ""
            decisionMap.value.clear()
        }


    }
}

@Composable
fun ColumnScope.GameRow(
    rowNumber: Int, map: HashMap<String, String>, call: (String, String) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .weight(1F)
    ) {
        Box(
            modifier = Modifier
                .fillMaxHeight()
                .weight(1F)
                .background(Color.Green)
                .clickable {
                    call("${rowNumber}1", "O")
                }, contentAlignment = Alignment.Center
        ) {

            Text(
                text = map["${rowNumber}1"] ?: "Play",
                fontSize = 30.sp,
                color = Color.White,
                fontWeight = FontWeight.Bold
            )
        }
        Spacer(
            modifier = Modifier
                .width(2.dp)
                .fillMaxHeight()
                .background(Color.White)
        )
        Box(
            modifier = Modifier
                .fillMaxHeight()
                .weight(1F)
                .background(Color.Green)
                .clickable {
                    call("${rowNumber}2", "X")
                }, contentAlignment = Alignment.Center
        ) {

            Text(
                text = map.getOrElse("${rowNumber}2") { "Play" },
                fontSize = 30.sp,
                color = Color.White,
                fontWeight = FontWeight.Bold
            )
        }
        Spacer(
            modifier = Modifier
                .width(2.dp)
                .fillMaxHeight()
                .background(Color.White)
        )
        Box(
            modifier = Modifier
                .fillMaxHeight()
                .weight(1F)
                .background(Color.Green)
                .clickable {
                    call("${rowNumber}3", "O")
                }, contentAlignment = Alignment.Center
        ) {

            Text(
                text = map.getOrElse("${rowNumber}3") { "Play" },
                fontSize = 30.sp,
                color = Color.White,
                fontWeight = FontWeight.Bold
            )
        }
    }


}

@Composable
fun ColumnScope.ShowDailog(title: String, message: String, isShowing: Boolean, call: () -> Unit) {

    if (isShowing) {
        AlertDialog(onDismissRequest = {
            call()
        },
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            shape = RoundedCornerShape(12.dp),
            buttons = {
                Button(
                    onClick = {
                        call()

                    }, modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .padding(10.dp)

                ) {
                    Text(text = "Play Again", fontSize = 20.sp)
                }
            },
            title = {
                Text(text = "Congrats Player $message", fontSize = 24.sp)
            },
            text = {
                Text(text = "Winner is Player $message", fontSize = 20.sp)
            })
    }


}


fun gameWinDecision(atKey: String, value: String, map: HashMap<String, String>): Boolean {
    if (atKey == "01") {
        return (map["02"] == value && map["03"] == value) || (map["11"] == value && map["21"] == value) || (map["12"] == value && map["23"] == value)
    }

    if (atKey == "02") {
        return (map["01"] == value && map["03"] == value) || (map["12"] == value && map["22"] == value)
    }

    if (atKey == "03") {
        return (map["01"] == value && map["02"] == value) || (map["13"] == value && map["23"] == value)
    }
    if (atKey == "11") {
        return (map["12"] == value && map["13"] == value) || (map["01"] == value && map["21"] == value)
    }

    if (atKey == "12") {
        return (map["11"] == value && map["13"] == value) || (map["02"] == value && map["22"] == value) || (map["01"] == value && map["23"] == value) || (map["03"] == value && map["21"] == value)
    }

    if (atKey == "13") {
        return (map["12"] == value && map["11"] == value) || (map["03"] == value && map["23"] == value)
    }
    if (atKey == "21") {
        return (map["11"] == value && map["01"] == value) || (map["22"] == value && map["23"] == value) || (map["12"] == value && map["03"] == value)
    }

    if (atKey == "22") {
        return (map["23"] == value && map["21"] == value) || (map["02"] == value && map["12"] == value)
    }

    if (atKey == "23") {
        return (map["21"] == value && map["22"] == value) || (map["13"] == value && map["03"] == value) || (map["12"] == value && map["01"] == value)
    }

    return atKey.isEmpty()
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    TicTacToTheme {
        TicTacTo(modifier = Modifier.padding(4.dp))
    }
}