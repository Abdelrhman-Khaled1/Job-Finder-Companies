package com.example.jobfindercompanies

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView

    class SplashScreens : AppCompatActivity() {
        private var numOpen = 0
        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_splash_screens)

            val mainImage = findViewById<ImageView>(R.id.im_splash)
            val theBelowImage = findViewById<ImageView>(R.id.belowImage)
            val tv = findViewById<TextView>(R.id.tvSplash)

            val btn_next = findViewById<ImageButton>(R.id.btn_next)

            btn_next.setOnClickListener(View.OnClickListener {
                when (numOpen) {
                    0 -> {
                        mainImage.setImageResource(R.drawable.second_splash)
                        theBelowImage.setImageResource(R.drawable.second_page)
                        tv.text = "As a Co-operate/Start-Up\nYou can find many employees"
                        numOpen++
                    }
                    1 -> {
                        mainImage.setImageResource(R.drawable.third_splash)
                        theBelowImage.setImageResource(R.drawable.third_page)
                        tv.text = "We provide jobs for\nPhysically Disabled \nPeople"
                        numOpen++
                    }
                    else -> {
                        val intent = Intent(this, Login::class.java)
                        startActivity(intent)
                        finish()
                    }
                }
            })
        }
    }