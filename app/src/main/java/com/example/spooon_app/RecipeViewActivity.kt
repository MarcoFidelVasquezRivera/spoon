package com.example.spooon_app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import com.example.spooon_app.databinding.ActivityCreateRecipeBinding
import com.example.spooon_app.databinding.ActivityRecipeViewBinding

class RecipeViewActivity : AppCompatActivity() {

    private val binding: ActivityRecipeViewBinding by lazy{
        ActivityRecipeViewBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val launcher = registerForActivityResult(ActivityResultContracts.StartActivityForResult(),::onResult)
        binding.creatorprofile.setOnClickListener {
            val intent = Intent(this, ProfileCreatorActivity::class.java)
            launcher.launch(intent)
        }
    }

    fun onResult(result:ActivityResult){

    }

}