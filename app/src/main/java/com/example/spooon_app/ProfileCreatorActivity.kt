package com.example.spooon_app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.example.spooon_app.databinding.ActivityProfileCreatorBinding
import com.example.spooon_app.databinding.ActivityRecipeViewBinding
import com.example.spooon_app.model.User
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

class ProfileCreatorActivity : AppCompatActivity() {
    private val binding: ActivityProfileCreatorBinding by lazy{
        ActivityProfileCreatorBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        var userId: String = ""
        userId = intent.extras?.getString("userID").toString()
        var me: User? = null
        lifecycleScope.launch(Dispatchers.Main) {
            val res = Firebase.firestore.collection("users").document(
                userId
            ).get().await()
            println(userId)
            println(res)
            me = res.toObject(User::class.java)!!
            binding.nameTV.text = me?.name
            binding.followersTV.text = me?.followers.toString()
            binding.descriptionTV.text = me?.description
        }

        binding.goBackBtn.setOnClickListener {
            setResult(RESULT_CANCELED)
            finish()
        }
    }
}