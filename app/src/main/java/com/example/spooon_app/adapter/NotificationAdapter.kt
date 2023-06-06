package com.example.spooon_app.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.example.spooon_app.R
import com.example.spooon_app.model.Notification
import com.example.spooon_app.viewholder.NotificationViewHolder

class NotificationAdapter : Adapter<NotificationViewHolder>() {

    private var notifications: ArrayList<Notification> = arrayListOf()

    init{
        notifications.add(Notification("","24/04/2003","Lorem ipsum to tre new message for you",""))
        notifications.add(Notification("","10/01/2020","Lorem ipsum ",""))
        notifications.add(Notification("","13/08/2032","Lorem ipsum to tre new message for youLorem ipsumLorem ipsumLorem ipsum",""))
        notifications.add(Notification("","15/10/2018","Lorem w message for you",""))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotificationViewHolder {
        val layoutInflater:LayoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.notification,parent,false)
        return NotificationViewHolder(view)
    }

    override fun onBindViewHolder(holder: NotificationViewHolder, position: Int) {
        holder.date.text = notifications[position].date
        holder.description.text = notifications[position].description
        //imagen
    }

    override fun getItemCount(): Int {
        return notifications.size
    }



}