package com.example.spooon_app.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.example.spooon_app.R
import com.example.spooon_app.model.Comment
import com.example.spooon_app.model.Recipe
import com.example.spooon_app.viewholder.CommentViewHolder
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class CommentAdapter : Adapter<CommentViewHolder>() {

    private var comments: ArrayList<Comment> = arrayListOf()
    private val db = Firebase.firestore

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

    fun loadComments(recipeId:String) {
        print(recipeId + "ASDASLFNJDSOWFBUJHIDSAHRIUAUOPHFDUPOSAHPFUOS")

        db.collection("comments").whereEqualTo("recipeId", recipeId).get()
            .addOnSuccessListener { documents ->
                for (document in documents) {
                    var comment = document.toObject(Comment::class.java)
                    comments.add(comment)
                }

                notifyDataSetChanged()
            }.addOnFailureListener { exception ->
                Log.w("error", "Error getting documents: ", exception)
            }
    }

}