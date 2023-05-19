package com.example.jobfindercompanies

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.database.FirebaseDatabase

class SignUp2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_sign_up2)

        val database = FirebaseDatabase.getInstance()
        val myRef = database.getReference("Companies")




         var company = intent.getSerializableExtra("firstPage") as? Company

//        Toast.makeText(this,company?.name, Toast.LENGTH_LONG ).show()

        val sign_up1 = findViewById<Button>(R.id.sign_up1)

        sign_up1.setOnClickListener(View.OnClickListener {

            val field = (findViewById<EditText>(R.id.field)).text.toString()
            val location = (findViewById<EditText>(R.id.location)).text.toString()
            val bio = (findViewById<EditText>(R.id.bio)).text.toString()
            val numberOfEmployees = findViewById<EditText>(R.id.numberOfEmployees).text.toString().toInt()


            company!!.field = field
            company.location = location
            company.bio = bio
            company.numberOfEmployees = numberOfEmployees


            val id = myRef.push().key
            company.id = id.toString()
            myRef.child(id.toString()).setValue(company)

            val lastIntent = Intent(this , HomePage::class.java)
            startActivity(lastIntent)

        })
    }
}