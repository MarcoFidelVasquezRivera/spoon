package com.example.spooon_app.adapter

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.example.spooon_app.R
import com.example.spooon_app.RecipeViewActivity
import com.example.spooon_app.model.Recipe
import com.example.spooon_app.viewholder.RecipeViewHolder
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class RecipeAdapter : Adapter<RecipeViewHolder>() {

    private var recipes: ArrayList<Recipe> = arrayListOf()
    private val db = Firebase.firestore

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {

        val layoutInflater:LayoutInflater = LayoutInflater.from(parent.context)
        val view: View = layoutInflater.inflate(R.layout.recipe, parent, false)
        val holder = RecipeViewHolder(view)
        return holder
    }

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {

        var recipe = recipes[position]
        holder.name.text = recipe.name
        holder.autor.text = recipe.userName
        holder.rating.text = recipe.rating.toString()
        holder.dificulty.text = recipe.dificulty
        //to do for diego

        holder.recipeBtn.setOnClickListener {
            holder.itemView.context.startActivity(
                Intent(holder.itemView.context,RecipeViewActivity::class.java)
            )
        }
    }

    override fun getItemCount(): Int {
        return recipes.size
    }

    fun loadRecipes(){

        db.collection("recipes").get().addOnSuccessListener {
                documents -> for (document in documents){
            recipes.add(document.toObject(Recipe::class.java))
            notifyDataSetChanged()
        }

        }.addOnFailureListener{
                exception -> Log.w("error", "Error getting documents: ", exception)
        }


    }
}