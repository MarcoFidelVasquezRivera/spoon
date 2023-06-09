package com.example.spooon_app

import android.content.Intent
import android.os.Bundle
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.spooon_app.adapter.CommentAdapter
import com.example.spooon_app.databinding.ActivityRecipeViewBinding
import com.example.spooon_app.databinding.CommentsFragmentBinding
import com.example.spooon_app.model.Comment
import com.example.spooon_app.model.User
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

class CommentsActivity : AppCompatActivity() {


    private val binding: CommentsFragmentBinding by lazy{
        CommentsFragmentBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        var adapter = CommentAdapter()
        binding.commentList.adapter = adapter
        binding.commentList.layoutManager = LinearLayoutManager(this)
        binding.commentList.setHasFixedSize(true)

        binding.commentsBackBtn.setOnClickListener {
            setResult(RESULT_OK)
            finish()
        }

        val launcher = registerForActivityResult(ActivityResultContracts.StartActivityForResult(),::onResult)
        var recipeId = intent.extras?.getString("recipeID").toString()

        binding.sendCommentBtn.setOnClickListener {
            var commentStr = binding.commentInput.text.toString()
            var commentID = java.util.UUID.randomUUID().toString()
            var usrObj: User? = null

            lifecycleScope.launch(Dispatchers.Main){
                val usr =  Firebase.firestore.collection("users").document(Firebase.auth.currentUser!!.uid)
                    .get().await()

                usrObj = usr.toObject(User::class.java)!!

                var comment: Comment = Comment(commentID,recipeId,commentStr, Firebase.auth.currentUser!!.uid, usrObj!!.name)

                Firebase.firestore
                    .collection("comments")
                    .document(comment.commentId)
                    .set(comment).await()
            }

            val intent = Intent(this, CommentsActivity::class.java)
            intent.putExtra("recipeID",recipeId)
            launcher.launch(intent)
            finish()
        }

        adapter.loadComments(recipeId)

    }

    fun onResult(result: ActivityResult){

    }

}