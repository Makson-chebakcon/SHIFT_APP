package com.example.shift_app.data.model


data class Location(
    val street: Street,
    val city: String,
    val state: String,
    val country: String,
    val postcode: String
)

data class Street(
    val number: Int,
    val name: String
)