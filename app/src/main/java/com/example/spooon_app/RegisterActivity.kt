package com.example.spooon_app

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.spooon_app.databinding.RegisteruserBinding
import com.example.spooon_app.model.User
import com.example.spooon_app.viewmodel.AuthViewModel

class RegisterActivity : AppCompatActivity(){
    private val binding: RegisteruserBinding by lazy{
        RegisteruserBinding.inflate(layoutInflater)
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
                    Toast.makeText(this, "Hubo un error", Toast.LENGTH_SHORT).show()
                }
            }
        }

        binding.registeruserBtn.setOnClickListener {
            viewmodel.signup(
                User(
                    null,
                    binding.usernameET.text.toString(),
                    binding.emailET.text.toString()
                ),
                binding.createpasswordET.text.toString()
            )

        }

        binding.backtologinBtn.setOnClickListener {
            startActivity(
                Intent(this, LoginActivity::class.java)
            )
            finish()
        }
    }

}