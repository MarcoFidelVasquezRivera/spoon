package com.example.spooon_app.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.example.spooon_app.R
import com.example.spooon_app.model.Recipe
import com.example.spooon_app.viewholder.RecipeViewHolder

class RecipeAdapter : Adapter<RecipeViewHolder>() {

    private var recipes: ArrayList<Recipe> = arrayListOf(
        Recipe("receta1","facil",1,2.5f, arrayListOf("i1","i2"), arrayListOf("p1","p2"),"Marco"),
        Recipe("receta2","dificil",1,4.5f, arrayListOf("i1","i2"), arrayListOf("p1","p2"),"Marco"),
        Recipe("receta3","dificil",1,4.5f, arrayListOf("i1","i2"), arrayListOf("p1","p2"),"Marco")
    )

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        val layoutInflater:LayoutInflater = LayoutInflater.from(parent.context)
        val view: View = layoutInflater.inflate(R.layout.recipe, parent, false)
        val holder = RecipeViewHolder(view)
        return holder
    }

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        var recipe = recipes[position]

        holder.name.text = recipe.name
        holder.autor.text = recipe.creator
        holder.rating.text = recipe.rating.toString()
        holder.dificulty.text = recipe.dificulty
        //to do for diego
    }

    override fun getItemCount(): Int {
        return recipes.size
    }
}