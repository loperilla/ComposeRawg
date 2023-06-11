package com.loperilla.rawg.coreui

sealed class Routes(val destination: String) {
    object HOME : Routes("home")
}