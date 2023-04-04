package com.example.spooon_app

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.spooon_app.databinding.MyRecipesFragmentBinding

class MyRecipesFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding:MyRecipesFragmentBinding = MyRecipesFragmentBinding.inflate(inflater, container, false)

        return binding.root
    }

    companion object{
        fun newInstance():MyRecipesFragment{
            return MyRecipesFragment()
        }
    }
}