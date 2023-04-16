package com.example.spooon_app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import com.example.spooon_app.databinding.ActivityCreateRecipeBinding
import com.example.spooon_app.databinding.ActivityMainBinding

class CreateRecipeActivity : AppCompatActivity() {

    private val binding: ActivityCreateRecipeBinding by lazy{
        ActivityCreateRecipeBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.goBackBtn.setOnClickListener {
            setResult(RESULT_CANCELED)
            finish()
        }

        val launcher = registerForActivityResult(ActivityResultContracts.StartActivityForResult(),::onResult)
        binding.createRecipeBtn.setOnClickListener {
            binding.createRecipeBtn.setOnClickListener {
                val intent = Intent(this, CreateRecipeStepsActivity::class.java)
                launcher.launch(intent)
            }
        }

    }
    fun onResult(result: ActivityResult){

    }
}