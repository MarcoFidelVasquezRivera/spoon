package com.example.spooon_app

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.CheckBox
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.result.ActivityResult
import androidx.appcompat.app.AppCompatActivity
import com.example.spooon_app.databinding.ActivityAddTagsBinding

class AddTagsActivity : AppCompatActivity() {

    private val binding: ActivityAddTagsBinding by lazy {
        ActivityAddTagsBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)

        setContentView(binding.root)

        binding.goBackBtn.setOnClickListener{
            setResult(RESULT_CANCELED)
            finish()
        }

        var title = intent.getStringExtra("title").toString()
        var difficulty = intent.getStringExtra("difficulty").toString()
        var ingredients = intent.getStringExtra("ingredients").toString()
        var image = Uri.parse(intent.getStringExtra("image"))

        val launcher = registerForActivityResult(ActivityResultContracts.StartActivityForResult(),::onResult)
        var tags:String = ""
        binding.addTagsDoneBtn.setOnClickListener {
            if (binding.mediterraneaCheckBox.isChecked){
                tags = tags + binding.mediterraneaCheckBox.text.toString() + ","
            }
            if (binding.italianaCheckBox.isChecked){
                tags = tags + binding.italianaCheckBox.text.toString() + ","
            }
            if (binding.espanolaCheckBox.isChecked){
                tags = tags + binding.espanolaCheckBox.text.toString() + ","
            }
            if (binding.colombianaCheckBox.isChecked){
                tags = tags + binding.colombianaCheckBox.text.toString() + ","
            }
            if (binding.vegetarianaCheckBox.isChecked){
                tags = tags + binding.vegetarianaCheckBox.text.toString() + ","
            }

            tags = tags.dropLast(1)
            println(tags)


            if(!tags.isEmpty()) {
                val intent = Intent(this, CreateRecipeStepsActivity::class.java)
                intent.putExtra("title", title)
                intent.putExtra("difficulty", difficulty)
                intent.putExtra("ingredients", ingredients)
                intent.putExtra("tags", tags)
                intent.putExtra("image", image.toString())
                launcher.launch(intent)
            }else{
                Toast.makeText(this, "Debe seleccionar al menos un Tag", Toast.LENGTH_LONG)
            }
        }


    }


    fun onResult(result: ActivityResult){

    }

}