package com.example.spooon_app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import com.example.spooon_app.databinding.ActivityCreateRecipeBinding
import com.example.spooon_app.databinding.ActivityCreateRecipeStepsBinding

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

        val launcher = registerForActivityResult(ActivityResultContracts.StartActivityForResult(),::onResult)
        binding.recipeDoneBtn.setOnClickListener {
            val intent = Intent(this, RecipeViewActivity::class.java)
            launcher.launch(intent)
        }

    }

    fun onResult(result: ActivityResult){

    }
}