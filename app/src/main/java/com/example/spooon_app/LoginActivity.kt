package com.example.spooon_app

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.spooon_app.databinding.ActivityRecipeViewBinding
import com.example.spooon_app.databinding.LoginBinding
import com.example.spooon_app.viewmodel.AuthViewModel

class LoginActivity : AppCompatActivity() {
    private val binding: LoginBinding by lazy{
        LoginBinding.inflate(layoutInflater)
    }
    val viewmodel: AuthViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        viewmodel.status.observe(this){
            when(it){
                2->{
                    startActivity(
                        Intent(this, MainActivity::class.java)
                    )
                    finish()
                }
                0->{
                    Toast.makeText(this, "No se ha podido iniciar sesión", Toast.LENGTH_SHORT).show()
                }
            }
        }

        binding.loginBtn.setOnClickListener {
            val email = binding.loginemailET.text.toString()
            val password = binding.passwordET.text.toString()
            viewmodel.login(email,password)
        }

        binding.registerBtn.setOnClickListener {
            startActivity(
                Intent(this,RegisterActivity::class.java)
            )
            finish()
        }
    }
}