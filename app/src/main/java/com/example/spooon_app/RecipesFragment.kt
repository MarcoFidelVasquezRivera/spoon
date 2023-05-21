package com.example.spooon_app

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.spooon_app.adapter.RecipeAdapter
import com.example.spooon_app.adapter.TagAdapter
import com.example.spooon_app.databinding.RecipesListFragmentBinding

class RecipesFragment :Fragment(){
    private lateinit var adapter:RecipeAdapter
    private lateinit var tagAdapter: TagAdapter
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding:RecipesListFragmentBinding = RecipesListFragmentBinding.inflate(inflater, container, false)

        adapter = RecipeAdapter()
        binding.recipesListRecyclerview.adapter = adapter
        binding.recipesListRecyclerview.layoutManager = LinearLayoutManager(activity)
        binding.recipesListRecyclerview.setHasFixedSize(true)

        tagAdapter = TagAdapter()
        binding.recipesTypeListRecyclerview.adapter = tagAdapter
        binding.recipesTypeListRecyclerview.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
        binding.recipesTypeListRecyclerview.setHasFixedSize(true)

        binding.menuBtn.setOnClickListener {
            if(!binding.tagRV.isVisible){
                binding.tagRV.visibility = View.VISIBLE;
                binding.tagRV.bringToFront();
            }else{
                binding.tagRV.visibility = View.INVISIBLE;
            }
        }

        return binding.root
    }

    companion object{
        fun newInstance():RecipesFragment{
            return RecipesFragment()
        }
    }

}