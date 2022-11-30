// Copyright 2000-2021 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.
import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState

@Composable
@Preview
fun App() {
    MaterialTheme {
        MainTest()
    }
}

@Composable
fun Test(
    roundUp: Boolean,
    onRoundUpChanged: (Boolean) -> Unit,
) {
    var selectedWifi by remember { mutableStateOf(false) }
    val colorWifi = if (selectedWifi) Color(0xFF138EFF) else Color(0xFFB1B1B1)

    var selectedBt by remember { mutableStateOf(false) }
    val colorBt = if (selectedBt) Color(0xFF138EFF) else Color(0xFFB1B1B1)

    Column (
        modifier = Modifier.padding(vertical = 10.dp, horizontal = 20.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        Row (
            modifier = Modifier.padding(top = 8.dp),
            horizontalArrangement = Arrangement.spacedBy(15.dp)
                ) {
            Button(
                modifier = Modifier.size(48.dp).aspectRatio(1f),
                contentPadding = PaddingValues(10.dp),
                shape = RoundedCornerShape(100),
                onClick = { selectedWifi = !selectedWifi},
                colors= ButtonDefaults.buttonColors(backgroundColor = colorWifi)
            ){
                Icon(
                    painter = painterResource("wifi.svg"),
                    contentDescription = "wifi",
                    tint = Color.White
                )
            }
            Button(
                modifier = Modifier.size(48.dp).aspectRatio(1f),
                contentPadding = PaddingValues(10.dp),
                shape = RoundedCornerShape(100),
                onClick = { selectedBt = !selectedBt },
                colors= ButtonDefaults.buttonColors(backgroundColor = colorBt)
            ){
                Icon(
                    painter = painterResource("bluetooth.svg"),
                    contentDescription = "bluetooth",
                    tint = Color.White
                )
            }

        }
        Column {
            Row (
                modifier = Modifier
                    .width(250.dp)
                    .height(48.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ){
                Text("Turn on when boot")
                Switch(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentWidth(Alignment.End),
                    checked = roundUp,
                    onCheckedChange = onRoundUpChanged,
                    colors = SwitchDefaults.colors(
                        uncheckedThumbColor = Color(0xFF999696),
                        checkedThumbColor = Color(0xFF138EFF),
                        checkedTrackColor = Color(0xFF9BCFFF),
                        uncheckedTrackColor = Color(0xFFC9C9C9),
                        checkedTrackAlpha = 1.0F,
                        uncheckedTrackAlpha = 1.0F
                    )
                )
            }
            Divider(
                color = Color.LightGray,
                modifier = Modifier
                    .padding(vertical = 5.dp)
                    .height(1.dp)
                    .width(250.dp)
            )
            Row (
                modifier = Modifier
                    .width(250.dp)
                    .height(48.dp),
                verticalAlignment = Alignment.CenterVertically,
            ){
                Text(text = "Hot key")
            }
        }
    }
}

@Composable
fun MainTest(){
    var roundUp by remember { mutableStateOf(false) }
    var wifi by remember { mutableStateOf(false) }
    var bluetooth by remember { mutableStateOf(false) }
    Test(
        roundUp = roundUp,
        onRoundUpChanged = { roundUp = it },
    )
}

fun main() = application {
    val windowState = rememberWindowState(size = DpSize.Unspecified)
    Window(onCloseRequest = ::exitApplication, title = "Copy_Pass", state = windowState) {
        window.isResizable = false
        App()
    }
}
