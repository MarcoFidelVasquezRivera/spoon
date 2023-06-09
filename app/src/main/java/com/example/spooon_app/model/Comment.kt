package com.example.spooon_app.model

data class Comment(
    var commentId: String="",
    var recipeId:String="",
    var message: String="",
    var authorId:String=""
)