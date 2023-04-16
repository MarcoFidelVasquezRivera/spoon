package com.example.spooon_app.viewholder

import android.view.View
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.spooon_app.databinding.RecipeBinding

class RecipeViewHolder(root: View) : ViewHolder(root){
    private val binding = RecipeBinding.bind(root)

    //image que aún no la manejo
    //nombre
    val name = binding.recipeNameTV
    //autor
    val autor = binding.autorNameTV
    //rating
    val rating = binding.recipeScoreTV
    //dificultad
    val dificulty = binding.recipeDifficultyTV

    val recipeBtn = binding.recipe
}