package com.oshaev.artclub.ui.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.oshaev.artclub.R
import android.content.Intent
import android.net.Uri
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.net.toUri
import com.bumptech.glide.Glide

class PostPriviewActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.post_item_layout);

        var postTitle: String? = intent.getStringExtra("postTitle")
        var titleImgUrl: String? = intent.getStringExtra("titleImgUrl")
        var titleTextView: TextView = findViewById(R.id.postTitleTextView)
        titleTextView.setText(postTitle)
        var titleImage:ImageView = findViewById(R.id.postImagePreview)
                titleImage.setImageURI(Uri.parse(titleImgUrl))
        Toast.makeText(this, ""+titleImgUrl,Toast.LENGTH_SHORT);




    }
}
