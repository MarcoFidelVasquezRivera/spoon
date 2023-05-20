package com.example.spooon_app.model

data class Recipe(
    var name:String,
    var dificulty:String,
    var image:Int,
    var rating:Float,
    var ingredients:List<String>,
    var steps:List<String>,
    var userId:String,
    var comments:List<Comment> = arrayListOf(),
    var tags:List<Tag> = arrayListOf()
)