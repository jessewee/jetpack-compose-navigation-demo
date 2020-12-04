package com.example.testcompose

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable

/** 页面路径 */
sealed class Destination {
    object Launcher : Destination()

    object Home : Destination()

    @Immutable
    data class Second(val bottom: Int) : Destination()

    /** 路径到页面的映射 */
    @Composable
    fun Page() {
        when (this) {
            Launcher -> LaunchPage()
            Home -> HomePage()
            is Second -> SecondPage(this.bottom)
        }
    }
}