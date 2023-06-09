package com.example.spooon_app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.example.spooon_app.databinding.ActivityCreateRecipeBinding
import com.example.spooon_app.databinding.ActivityRecipeViewBinding
import com.example.spooon_app.model.Recipe
import com.example.spooon_app.model.User
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

class RecipeViewActivity : AppCompatActivity() {

    private val binding: ActivityRecipeViewBinding by lazy{
        ActivityRecipeViewBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.backBtn.setOnClickListener {
            setResult(RESULT_CANCELED)
            finish()
        }

        var recipeId:String = ""
        recipeId = intent.extras?.getString("id").toString()
        var recipe: Recipe? = null
        lifecycleScope.launch(Dispatchers.Main) {
            val res = Firebase.firestore.collection("recipes").document(
                recipeId
            ).get().await()
            recipe = res.toObject(Recipe::class.java)!!
            binding.recipeViewChefNameTxt.text = recipe!!.userName

            Firebase.storage.reference.child(recipe!!.image).downloadUrl.addOnSuccessListener {
                var imageURL = it.toString()
                Glide.with(applicationContext).load(imageURL).into(binding.recipeViewImage);
            }

            binding.recipeViewNameTxt.text = recipe!!.name
            binding.recipeViewLevelTxt.text = recipe!!.dificulty
            var text = recipe!!.ingredients + "\n"
            text += recipe!!.steps
            binding.stepsTV.text = text

        }


        val launcher = registerForActivityResult(ActivityResultContracts.StartActivityForResult(),::onResult)
        binding.creatorprofile.setOnClickListener {
            val intent = Intent(this, ProfileCreatorActivity::class.java)
            intent.putExtra("userID",recipe!!.userId)
            launcher.launch(intent)
        }

        binding.recipeViewCommentBtn.setOnClickListener {
            if(!binding.constraintLayout5){
                binding.tagsConstraintLayout.visibility = View.VISIBLE;
                binding.tagsConstraintLayout.bringToFront();
            }else{
                checkCheckboxes(binding)
                adapter.loadCustomRecipes(tags)
                binding.tagsConstraintLayout.visibility = View.INVISIBLE;
            }
        }

    }

    fun onResult(result:ActivityResult){

    }

}