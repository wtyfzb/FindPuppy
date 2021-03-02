package com.example.androiddevchallenge

data class Dogs(
    val dogs: List<Dog>,
)

data class Dog(
    val name: String,
    val age: String,
    val sex: String,
    val photoUrl: String,
    val desc: String
)