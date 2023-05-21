package com.example.spooon_app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
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
        binding.notificationBtn.setOnClickListener {
            if(!binding.notificationRV.isVisible){
                binding.notificationRV.visibility = View.VISIBLE;
                binding.notificationRV.bringToFront();
            }else{
                binding.notificationRV.visibility = View.INVISIBLE;
            }
        }


        showFragment(recipes)

        binding.navBar.setOnItemSelectedListener {
            when(it.itemId){
                R.id.recipemenu->{showFragment(recipes)}
                R.id.myrecipemenu->{showFragment(myRecipes)}
                R.id.profilemenu->{showFragment(profile)}
            }
            true
        }

    }

    fun showFragment(fragment: Fragment){
        supportFragmentManager.beginTransaction().replace(R.id.fragmentContainer, fragment).commit()
    }
}