package com.example.testcompose

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Text
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.dp
import androidx.ui.tooling.preview.Preview
import java.util.*

@Preview
@Composable
fun PagePreview() {
    HomePage()
}

@Composable
fun LaunchPage() {
    val seconds = mutableStateOf(11)
    val timer = Timer().apply {
        schedule(object : TimerTask() {
            override fun run() {
                seconds.value--
                if (seconds.value == 0) {
                    cancel()
                    navigator.pushReplace(Destination.Home)
                }
            }
        }, 1000, 1000)
    }
    Box(modifier = Modifier.background(color = Color.White).fillMaxSize()) {
        Button(onClick = {
            timer.cancel()
            navigator.pushReplace(Destination.Home)
        }) {
            Text(text = "下一页")
        }
        cd(seconds.value)
    }
}

@Composable
fun cd(seconds: Int) {
    Canvas(modifier = Modifier.fillMaxSize()) {
        val left = 500F
        val top = 500F
        val step = 100F
        val path = Path().apply {
            moveTo(left, top)
            when (seconds) {
                9 -> {
                    moveTo(left, top + step * 2)
                    lineTo(left + step, top + step * 2)
                    lineTo(left + step, top)
                    lineTo(left, top)
                    lineTo(left, top + step)
                    lineTo(left + step, top + step)
                }
                8 -> {
                    lineTo(left + step, top)
                    lineTo(left + step, top + step * 2)
                    lineTo(left, top + step * 2)
                    lineTo(left, top)
                    moveTo(left, top + step)
                    lineTo(left + step, top + step)
                }
                7 -> {
                    lineTo(left + step, top)
                    lineTo(left + step, top + step * 2)
                }
                6 -> {
                    moveTo(left + step, top)
                    lineTo(left, top)
                    lineTo(left, top + step)
                    lineTo(left + step, top + step)
                    lineTo(left + step, top + step * 2)
                    lineTo(left, top + step * 2)
                    lineTo(left, top + step)
                }
                5 -> {
                    moveTo(left + step, top)
                    lineTo(left, top)
                    lineTo(left, top + step)
                    lineTo(left + step, top + step)
                    lineTo(left + step, top + step * 2)
                    lineTo(left, top + step * 2)
                }
                4 -> {
                    lineTo(left, top + step)
                    lineTo(left + step, top + step)
                    moveTo(left + step, top)
                    lineTo(left + step, top + step * 2)
                }
                3 -> {
                    lineTo(left + step, top)
                    lineTo(left + step, top + step * 2)
                    lineTo(left, top + step * 2)
                    moveTo(left, top + step)
                    lineTo(left + step, top + step)
                }
                2 -> {
                    lineTo(left + step, top)
                    lineTo(left + step, top + step)
                    lineTo(left, top + step)
                    lineTo(left, top + step * 2)
                    lineTo(left + step, top + step * 2)
                }
                1 -> {
                    moveTo(left + step, top)
                    lineTo(left + step, top + step * 2)
                }
                else -> {
                    lineTo(left + step, top + step * 2)
                }
            }
        }
        drawPath(path, Color.Red, style = Stroke(width = 5F, cap = StrokeCap.Round, join = StrokeJoin.Round))
    }
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