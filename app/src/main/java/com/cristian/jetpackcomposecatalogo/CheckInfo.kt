package com.cristian.jetpackcomposecatalogo

data class CheckInfo(
    val title: String,
    val checked: Boolean = false,
    var onCheckedChange: (Boolean) -> Unit = {}
)
