package com.example.spooon_app

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.spooon_app.adapter.RecipeAdapter
import com.example.spooon_app.adapter.TagAdapter
import com.example.spooon_app.databinding.MyRecipesFragmentBinding

class MyRecipesFragment : Fragment() {
    private lateinit var adapter:RecipeAdapter
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding:MyRecipesFragmentBinding = MyRecipesFragmentBinding.inflate(inflater, container, false)

        adapter = RecipeAdapter()
        binding.myrecipeRV.adapter = adapter
        binding.myrecipeRV.layoutManager = LinearLayoutManager(activity)
        binding.myrecipeRV.setHasFixedSize(true)

        val launcher = registerForActivityResult(ActivityResultContracts.StartActivityForResult(),::onResult)
        binding.createRecipeBtn.setOnClickListener {
            val intent = Intent(activity, CreateRecipeActivity::class.java)
            launcher.launch(intent)
        }

        adapter.loadMyRecipes()
        return binding.root
    }

    fun onResult(result:ActivityResult){

    }

    companion object{
        fun newInstance():MyRecipesFragment{
            return MyRecipesFragment()
        }
    }
}