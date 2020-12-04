package com.example.testcompose

import androidx.activity.OnBackPressedCallback
import androidx.activity.OnBackPressedDispatcher
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf

/** 页面导航 */
@Suppress("unused")
class Navigator(initialDestination: Destination, private val backPressedDispatcher: OnBackPressedDispatcher) {
    private val _destinations = mutableStateListOf(initialDestination)
    private val _currentDestination = mutableStateOf(initialDestination)
    val destinations: List<Destination> = _destinations
    val currentDestination: State<Destination> = _currentDestination
    private val canGoBack: Boolean get() = _destinations.size > 1
    private val backPressedCallback = object : OnBackPressedCallback(canGoBack) {
        override fun handleOnBackPressed() = pop()
    }.also { backPressedDispatcher.addCallback(it) }

    fun push(destination: Destination) {
        _destinations.add(destination)
        _currentDestination.value = destination
        backPressedCallback.isEnabled = canGoBack
    }

    fun pushReplace(destination: Destination) {
        _destinations.removeLast()
        push(destination)
    }

    fun pop() {
        _destinations.removeLast()
        backPressedCallback.isEnabled = canGoBack
        _currentDestination.value = _destinations.last()
    }

    fun popUntil(destination: Destination, include: Boolean = true) {
        val matchedIdx = _destinations.indexOfLast { it == destination }
        doPopUntil(matchedIdx, include)
    }

    fun popUntilLast(destination: Destination, include: Boolean = true) {
        val matchedIdx = _destinations.indexOfFirst { it == destination }
        doPopUntil(matchedIdx, include)
    }

    private fun doPopUntil(matchedIdx: Int, include: Boolean) {
        if (matchedIdx < 0) return
        val popIdx = if (include) matchedIdx else matchedIdx + 1
        if (popIdx >= _destinations.size) return
        if (popIdx == 0) {
            backPressedDispatcher.onBackPressed()
            return
        }
        _destinations.removeRange(popIdx, _destinations.size)
        backPressedCallback.isEnabled = canGoBack
        _currentDestination.value = _destinations.last()
    }
}