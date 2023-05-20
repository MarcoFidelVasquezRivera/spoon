package com.example.spooon_app.model

import java.io.Serializable

data class User(
    var user_id: String? = "",
    var name:String = "",
    var email:String = "",
    var score: Double = 0.0,
    var followers:Int = 0,
    var description:String = "mi descripción",
    var recipes:List<Recipe> = arrayListOf(),
    var notifications:List<Notification> = arrayListOf()
) : Serializable{
    override fun toString():String{
        return name
    }
}