package com.example.jobfindercompanies

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btn : Button = findViewById(R.id.btn)

        btn.setOnClickListener(View.OnClickListener {
            val intent = Intent(this, SplashScreens::class.java)
            startActivity(intent)
            finish()
        })
    }
}