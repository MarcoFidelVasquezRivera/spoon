package com.example.spooon_app.model

data class Recipe(
    var id:String = "",
    var name:String = "",
    var dificulty:String = "",
    var image:String = "",
    var rating: Double = 0.0,
    var ingredients:String = "",
    var steps:String = "",
    var userId:String = "",
    var userName:String = "",
    var tags:ArrayList<String> = arrayListOf()
)