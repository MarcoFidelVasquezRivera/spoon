package com.example.spooon_app

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.spooon_app.adapter.RecipeAdapter
import com.example.spooon_app.adapter.TagAdapter
import com.example.spooon_app.databinding.MyRecipesFragmentBinding

class MyRecipesFragment : Fragment() {
    private lateinit var adapter:RecipeAdapter
    private lateinit var tagAdapter:TagAdapter
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

        tagAdapter = TagAdapter()
        binding.recipesTypeListRecyclerview.adapter = tagAdapter
        binding.recipesTypeListRecyclerview.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
        binding.recipesTypeListRecyclerview.setHasFixedSize(true)

        return binding.root
    }

    companion object{
        fun newInstance():MyRecipesFragment{
            return MyRecipesFragment()
        }
    }
}