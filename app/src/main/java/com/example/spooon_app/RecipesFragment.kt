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
    private var tags:ArrayList<String> = arrayListOf()
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

        binding.menuBtn.setOnClickListener {
            if(!binding.tagsConstraintLayout.isVisible){
                binding.tagsConstraintLayout.visibility = View.VISIBLE;
                binding.tagsConstraintLayout.bringToFront();
            }else{
                checkCheckboxes(binding)
                adapter.loadCustomRecipes(tags)
                binding.tagsConstraintLayout.visibility = View.INVISIBLE;
            }
        }

        adapter.loadRecipes()



        return binding.root
    }

    companion object{
        fun newInstance():RecipesFragment{
            return RecipesFragment()
        }
    }

    fun checkCheckboxes(binding: RecipesListFragmentBinding){
        if (binding.mediterraneaCB.isChecked){
            tags.add(binding.mediterraneaCB.text.toString())
        }
        if (binding.italianaCB.isChecked){
            tags.add(binding.italianaCB.text.toString())
        }
        if (binding.espanolaCB.isChecked){
            tags.add(binding.espanolaCB.text.toString())
        }
        if (binding.colombianaCB.isChecked){
            tags.add(binding.colombianaCB.text.toString())
        }
        if (binding.vegetarianaCB.isChecked){
            tags.add(binding.vegetarianaCB.text.toString())
        }
    }


}