package com.example.spooon_app.model

data class Notification(
    var notifier_id:String = "",
    var date:String,
    var description:String,
    var image:String = ""
)