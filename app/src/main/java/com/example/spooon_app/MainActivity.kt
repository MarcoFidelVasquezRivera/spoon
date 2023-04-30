package com.example.spooon_app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.example.spooon_app.databinding.ActivityMainBinding
import com.example.spooon_app.model.User
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {

    private val binding:ActivityMainBinding by lazy{
        ActivityMainBinding.inflate(layoutInflater)
    }

    private val recipes = RecipesFragment.newInstance()
    private val myRecipes = MyRecipesFragment.newInstance()
    private val profile = ProfileFragment.newInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        showFragment(recipes)

        binding.navBar.setOnItemSelectedListener {
            when(it.itemId){
                R.id.recipemenu->{showFragment(recipes)}
                R.id.myrecipemenu->{showFragment(myRecipes)}
                R.id.profilemenu->{showFragment(profile)}
            }
            true
        }

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
                    val me = res.toObject(User::class.java)
                    withContext(Dispatchers.Main){
                        Toast.makeText(this@MainActivity,
                            me?.name,
                            Toast.LENGTH_SHORT)
                            .show()
                    }
                }catch (e:Exception){
                    Toast.makeText(this@MainActivity, "No tiene sesion iniciada", Toast.LENGTH_SHORT).show()
                    startActivity(
                        Intent(this@MainActivity, LoginActivity::class.java)
                    )
                    finish()
                }

            }
        }

    }

    fun showFragment(fragment: Fragment){
        supportFragmentManager.beginTransaction().replace(R.id.fragmentContainer, fragment).commit()
    }
}