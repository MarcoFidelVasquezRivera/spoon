package com.example.spooon_app.adapter


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.example.spooon_app.R
import com.example.spooon_app.model.Tag
import com.example.spooon_app.viewholder.TagViewHolder

class TagAdapter : Adapter<TagViewHolder>(){

    var tags: ArrayList<Tag> = arrayListOf(
        Tag("Colombiana"),
        Tag("Carne"),
        Tag("Facil")

    )
    var tagsLiveData = MutableLiveData(tags)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TagViewHolder {
        val layoutInflater:LayoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.recipe_type_btn, parent,false)
        val holder = TagViewHolder(view)
        return holder

    }

    override fun onBindViewHolder(holder: TagViewHolder, position: Int) {
        holder.tagName.text = tags[position].name
    }

    override fun getItemCount(): Int {
        return tags.size
    }
}