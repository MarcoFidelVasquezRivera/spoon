package com.example.spooon_app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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
    }
}