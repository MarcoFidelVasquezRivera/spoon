package com.example.spooon_app.model

data class Recipe(
    var id:String = "",
    var name:String = "",
    var dificulty:String = "",
    var image:Int = 0,
    var rating: Double = 0.0,
    var ingredients:String = "",
    var steps:String = "",
    var userId:String = "",
    var comments:List<Comment> = arrayListOf(),
    var tags:List<Tag> = arrayListOf()
)