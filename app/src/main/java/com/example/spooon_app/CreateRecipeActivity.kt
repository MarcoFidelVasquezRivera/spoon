package com.example.spooon_app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import com.example.spooon_app.databinding.ActivityCreateRecipeBinding
import com.example.spooon_app.model.Recipe

class CreateRecipeActivity : AppCompatActivity() {

    private val binding: ActivityCreateRecipeBinding by lazy{
        ActivityCreateRecipeBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.goBackBtn.setOnClickListener {
            setResult(RESULT_CANCELED)
            finish()
        }

        val launcher = registerForActivityResult(ActivityResultContracts.StartActivityForResult(),::onResult)
        binding.createRecipeBtn.setOnClickListener {
            var title = binding.tittleET.text.toString()
            var difficulty = binding.difficultySpinner.selectedItem.toString();
            var ingredients = binding.ingredientsET.text.toString()

            val intent = Intent(this, AddTagsActivity::class.java)
            intent.putExtra("title",title)
            intent.putExtra("difficulty", difficulty)
            intent.putExtra("ingredients",ingredients)
            launcher.launch(intent)
        }

    }
    fun onResult(result: ActivityResult){

    }
}