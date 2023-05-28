package com.example.spooon_app

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.spooon_app.databinding.RegisteruserBinding
import com.example.spooon_app.databinding.WelcomePageBinding
import com.example.spooon_app.model.User
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext

class WelcomePageActivity : AppCompatActivity() {

    private val binding: WelcomePageBinding by lazy{
        WelcomePageBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        if(Firebase.auth.currentUser==null){
            startActivity(
                Intent(this, LoginActivity::class.java)
            )
            finish()
        }else{
            lifecycleScope.launch(Dispatchers.Main) {
                try {
                    val res = Firebase.firestore.collection("users").document(
                        Firebase.auth.currentUser!!.uid
                    ).get().await()
                    //Aqui iba lo del Toast
                    startActivity(
                        Intent(this@WelcomePageActivity, MainActivity::class.java)
                    )
                    finish()
                }catch (e:Exception){
                    Toast.makeText(this@WelcomePageActivity, "No tiene sesion iniciada", Toast.LENGTH_SHORT).show()
                    startActivity(
                        Intent(this@WelcomePageActivity, LoginActivity::class.java)
                    )
                    finish()
                }

            }
        }
    }
}