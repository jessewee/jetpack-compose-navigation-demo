package com.example.testcompose

import androidx.activity.OnBackPressedDispatcher
import androidx.compose.animation.Crossfade
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.gesture.tapGestureFilter
import androidx.compose.ui.graphics.Color
import androidx.ui.tooling.preview.Preview
import com.example.testcompose.ui.AppComposeTheme

private lateinit var mNavigator: Navigator
val navigator: Navigator get() = mNavigator

/** App入口 */
@Composable
fun App(backDispatcher: OnBackPressedDispatcher) {
    mNavigator = remember { Navigator(Destination.Launcher, backDispatcher) }
    AppComposeTheme { Pages(navigator.currentDestination.value) }
}

@Composable
fun Pages(currentDestination: Destination) {
    Box(modifier = Modifier.background(color = Color.White).fillMaxSize()) {
        // 底层的页面
        if (navigator.destinations.size > 1) {
            navigator.destinations.subList(0, navigator.destinations.size - 1)
            for (i in 0 until navigator.destinations.size - 1) navigator.destinations[i].Page()
        }
        // 全屏Box，用来遮挡底层页面，屏蔽底层页面的点击
        Box(modifier = Modifier.background(color = Color.White).fillMaxSize().tapGestureFilter { })
        // 当前页
        Crossfade(currentDestination) { currentDestination.Page() }
    }
}

@Preview
@Composable
fun AppPreview() {
    App(OnBackPressedDispatcher())
}