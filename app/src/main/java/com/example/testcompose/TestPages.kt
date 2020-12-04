package com.example.testcompose

import androidx.compose.foundation.Text
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import java.util.*


@Composable
fun LaunchPage() {
    Text("启动页")
    Timer().schedule(object : TimerTask() {
        override fun run() {
            navigator.pushReplace(Destination.Home)
        }
    }, 3000)
}

@Composable
fun HomePage() {
    Box(alignment = Alignment.Center, modifier = Modifier.background(color = Color(0x25000000)).fillMaxSize()) {
        Button(onClick = {
            navigator.push(Destination.Second(0))
        }) {
            Text(text = "下一页")
        }

    }
}

@Composable
fun SecondPage(bottom: Int) {
    Column(
            horizontalAlignment = Alignment.End,
            verticalArrangement = Arrangement.Bottom,
            modifier = Modifier.background(color = Color(0x25000000)).fillMaxSize().padding(bottom = bottom.dp)
    ) {
        Text(text = bottom.toString())
        Button(onClick = { navigator.pop() }) {
            Text(text = "返回")
        }
        Button(onClick = {
            val b = bottom + 50
            navigator.push(Destination.Second(b))
        }) {
            Text(text = "下一页")
        }
    }
}