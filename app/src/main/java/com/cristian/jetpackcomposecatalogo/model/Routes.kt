package com.cristian.jetpackcomposecatalogo.model

sealed class Routes(val route: String) {
    object Screen1 : Routes("pantalla1")
    object Screen2 : Routes("pantalla2")
    object Screen3 : Routes("pantalla3")
    object Screen4 : Routes("pantalla4/{age}") {
        fun createRoute(age: Int) = "pantalla4/$age"
    }
    object Screen5 : Routes("pantalla5?name={name}") {
        fun createRoute(name: String) = "pantalla5?name=$name"
    }
}
