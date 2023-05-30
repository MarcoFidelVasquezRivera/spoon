package com.example.spooon_app.adapter

import android.content.Intent
import android.net.Uri
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.bumptech.glide.Glide
import com.example.spooon_app.R
import com.example.spooon_app.RecipeViewActivity
import com.example.spooon_app.model.Recipe
import com.example.spooon_app.viewholder.RecipeViewHolder
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import kotlinx.coroutines.tasks.await

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

        println("CCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCC" + Firebase.storage.reference.child(recipe.image))

        Firebase.storage.reference.child(recipe.image).downloadUrl.addOnSuccessListener {
                var imageURL = it.toString()
                Glide.with(holder.image.context).load(imageURL).into(holder.image);
        }

        holder.autor.text = recipe.userName
        holder.rating.text = recipe.rating.toString()
        holder.dificulty.text = recipe.dificulty
        //to do for diego

        holder.recipeBtn.setOnClickListener {
            var intent = Intent(holder.itemView.context,RecipeViewActivity::class.java)
            intent.putExtra("id",recipe.id)
            holder.itemView.context.startActivity(
                intent
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

    fun loadMyRecipes(){
        var userid = Firebase.auth.currentUser!!.uid
        db.collection("recipes").whereEqualTo("userId", userid).get().addOnSuccessListener {
                documents -> for (document in documents){
            recipes.add(document.toObject(Recipe::class.java))
            notifyDataSetChanged()
        }

        }.addOnFailureListener{
                exception -> Log.w("error", "Error getting documents: ", exception)
        }


    }

    fun loadCustomRecipes(userTags : ArrayList<String>){
        recipes.clear()

        if(userTags.isNotEmpty()){
            for (tag in userTags) {
                db.collection("recipes").whereArrayContains("tags", tag).get()
                    .addOnSuccessListener { documents ->
                        for (document in documents) {
                            var recipe = document.toObject(Recipe::class.java)
                            if(!recipes.contains(recipe)){
                                recipes.add(recipe)

                            }
                        }

                        notifyDataSetChanged()
                    }.addOnFailureListener { exception ->
                    Log.w("error", "Error getting documents: ", exception)
                }
            }
        }else{
            println(recipes.size)
            db.collection("recipes").get()
                .addOnSuccessListener { documents ->
                    for (document in documents) {
                        var recipe = document.toObject(Recipe::class.java)
                        println(recipes.size)
                        if(!recipes.contains(recipe)){
                            println("ENTREEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEE al loop :)")
                            recipes.add(recipe)

                        }
                    }

                    notifyDataSetChanged()
                }.addOnFailureListener { exception ->
                    Log.w("error", "Error getting documents: ", exception)
                }
        }

    }
}