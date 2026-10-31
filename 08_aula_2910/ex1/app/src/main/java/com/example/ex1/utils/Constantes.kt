package com.example.ex1.utils

class Constantes {
    object BD_MSGS {
        val SUCCESS = 1
        val FAIL = 0
        val CONSTRAINT = 2
        val NOT_FOUND = 3
    }

    object MOCK_DATA {
        val ANIMAL_LIST : List<String> = listOf(
            "Cow", "Sheep", "Pig", "Wolf", "Lion",
            "Tiger", "Elephant", "Giraffe", "Zebra", "Monkey",
            "Bear", "Fox", "Rabbit", "Deer", "Horse",
            "Dog", "Cat", "Chicken", "Duck", "Penguin"
        )
    }
}