package com.example.spooon_app.viewholder

import android.view.View
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.spooon_app.databinding.CommentBinding

class CommentViewHolder(root: View) : ViewHolder(root) {

    private val binding = CommentBinding.bind(root)

   // val image = binding.userImageComment
    val usernameComment = binding.usernameComment
    //val  date = binding.dateComment
    val comment = binding.comment
}