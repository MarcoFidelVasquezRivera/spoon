package com.example.spooon_app.model

data class Recipe(
    var name:String,
    var dificulty:String,
    var image:Int,
    var rating:Float,
    var ingredients:List<String>,
    var steps:List<String>,
    var creator:String //cambiar a una referencia a la clase de ususrio
)