package com.example.spooon_app.viewholder

import android.view.View
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.spooon_app.databinding.RecipeTypeBtnBinding

class TagViewHolder(root:View) : ViewHolder(root) {

    private val binding:RecipeTypeBtnBinding = RecipeTypeBtnBinding.bind(root)

    val tagName = binding.recipeTypeName
    val deleteTag = binding.deleteTypeBtn


}