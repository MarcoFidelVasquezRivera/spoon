package com.example.spooon_app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import androidx.fragment.app.Fragment
import com.example.spooon_app.databinding.ActivityMainBinding

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
    }

    fun showFragment(fragment: Fragment){
        supportFragmentManager.beginTransaction().replace(R.id.fragmentContainer, fragment).commit()
    }
}