package com.example.spooon_app

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ScrollView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.example.spooon_app.databinding.ProfileFragmentBinding
import com.example.spooon_app.model.User
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

class ProfileFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): ScrollView {
        val binding:ProfileFragmentBinding = ProfileFragmentBinding.inflate(inflater, container, false)

        var me: User? = null
        lifecycleScope.launch(Dispatchers.Main) {
            val res = Firebase.firestore.collection("users").document(
                Firebase.auth.currentUser!!.uid
            ).get().await()
            me = res.toObject(User::class.java)!!
            Toast.makeText(activity, me?.name, Toast.LENGTH_SHORT).show()
            binding.nameTV.text = me?.name
            binding.followersTV.text = me?.followers.toString()
            binding.descriptionTV.text = me?.description
        }


        return binding.root
    }

    companion object{
        fun newInstance():ProfileFragment{
            return ProfileFragment()
        }
    }
}