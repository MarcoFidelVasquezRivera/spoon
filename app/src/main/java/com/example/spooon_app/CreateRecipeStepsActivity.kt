package com.example.spooon_app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.lifecycle.lifecycleScope
import com.example.spooon_app.databinding.ActivityCreateRecipeBinding
import com.example.spooon_app.databinding.ActivityCreateRecipeStepsBinding
import com.example.spooon_app.model.Recipe
import com.example.spooon_app.model.User
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

class CreateRecipeStepsActivity : AppCompatActivity() {

    private val binding: ActivityCreateRecipeStepsBinding by lazy{
        ActivityCreateRecipeStepsBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.goBackBtn.setOnClickListener {
            setResult(RESULT_CANCELED)
            finish()
        }

        var title = intent.getStringExtra("title").toString()
        var difficulty = intent.getStringExtra("difficulty").toString()
        var ingredients = intent.getStringExtra("ingredients").toString()
        var tags = intent.getStringExtra("tags")
        var tagsArr:ArrayList<String> = arrayListOf()
        if(tags!!.contains(",")){
           tagsArr  = tags?.split(",") as ArrayList<String>
        }else{
            tagsArr.add(tags)
        }

        var me: User? = null
        var recipes:ArrayList<Recipe> = arrayListOf()

        var userName = ""
        lifecycleScope.launch(Dispatchers.Main) {
            val result = Firebase.firestore.collection("users").document(
                Firebase.auth.currentUser!!.uid
            ).get().await()

           var userobj = result.toObject(User::class.java)
           userName = userobj?.name.toString()
        }


        val launcher = registerForActivityResult(ActivityResultContracts.StartActivityForResult(),::onResult)
        binding.recipeDoneBtn.setOnClickListener {
            var steps = binding.stepsET.text.toString()
            var userId = Firebase.auth.currentUser!!.uid



            var recipe = Recipe(title+userId,title,difficulty,0,0.0,ingredients,steps,userId, userName,tags=tagsArr)
            recipes.add(recipe)

            lifecycleScope.launch(Dispatchers.Main) {
                Firebase.firestore
                    .collection("recipes")
                    .document(recipe.id)
                    .set(recipe).await()
            }

            val intent = Intent(this, RecipeViewActivity::class.java)
            intent.putExtra("id",recipe.id)
            launcher.launch(intent)
            finish()
        }

    }

    fun onResult(result: ActivityResult){

    }
}