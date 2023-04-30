package com.example.spooon_app

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.spooon_app.databinding.ActivityRecipeViewBinding
import com.example.spooon_app.databinding.LoginBinding

class LoginActivity : AppCompatActivity() {
    private val binding: LoginBinding by lazy{
        LoginBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)


    }
}