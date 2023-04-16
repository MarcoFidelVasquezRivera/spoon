package com.example.spooon_app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.spooon_app.databinding.ActivityProfileCreatorBinding
import com.example.spooon_app.databinding.ActivityRecipeViewBinding

class ProfileCreatorActivity : AppCompatActivity() {
    private val binding: ActivityProfileCreatorBinding by lazy{
        ActivityProfileCreatorBinding.inflate(layoutInflater)
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