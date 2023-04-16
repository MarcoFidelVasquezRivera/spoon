package com.example.spooon_app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.spooon_app.databinding.ActivityCreateRecipeBinding
import com.example.spooon_app.databinding.ActivityRecipeViewBinding

class RecipeViewActivity : AppCompatActivity() {

    private val binding: ActivityRecipeViewBinding by lazy{
        ActivityRecipeViewBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

    }
}