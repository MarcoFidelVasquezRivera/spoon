package com.example.spooon_app.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.example.spooon_app.R
import com.example.spooon_app.model.Comment
import com.example.spooon_app.viewholder.CommentViewHolder

class CommentAdapter : Adapter<CommentViewHolder>() {

    private var comments: ArrayList<Comment> = arrayListOf()

    init{
        comments.add(Comment("a","Que rico plato", "06/06/2023", "idk"))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentViewHolder {

        val layoutInflater: LayoutInflater = LayoutInflater.from(parent.context)
        val view: View = layoutInflater.inflate(R.layout.comment, parent, false)

        val holder = CommentViewHolder(view)

        return holder

    }


    override fun onBindViewHolder(holder: CommentViewHolder, position: Int) {

        holder.usernameComment.text = comments[position].authorId
        holder.comment.text = comments[position].message
        //holder.image




    }

    override fun getItemCount(): Int {
        return  comments.size
    }

}