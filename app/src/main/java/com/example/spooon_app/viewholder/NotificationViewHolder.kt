package com.example.spooon_app.viewholder

import android.view.View
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.spooon_app.databinding.NotificationBinding

class NotificationViewHolder(root: View) : ViewHolder(root) {
    private val binding = NotificationBinding.bind(root)

    val image = binding.imageNotification
    val date = binding.dateNotificationTV
    val description = binding.descriptionNotificationTV
}