package com.example.jobfindercompanies

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class SignUp1 : AppCompatActivity() {   var mAuth : FirebaseAuth? = null



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up1)

        mAuth = FirebaseAuth.getInstance()


        val button = findViewById<Button>(R.id.sign_up1)
        button.setOnClickListener(View.OnClickListener {

            var company = Company()

            val name = findViewById<EditText>(R.id.name).text.toString()
            val email = findViewById<EditText>(R.id.email).text.toString()
            val password = findViewById<EditText>(R.id.password).text.toString()

            addData(email,password)

            company.name = name
            company.email = email

            val intent = Intent(this, SignUp2::class.java)
            intent.putExtra("firstPage",company)
            startActivity(intent)
        })
    }

    fun addData( email : String, password : String){
        if(email.isNotEmpty() && password.isNotEmpty()) {
            mAuth?.createUserWithEmailAndPassword(email, password)?.addOnSuccessListener {
//                Toast.makeText(applicationContext, "Success", Toast.LENGTH_LONG).show()
            }?.addOnFailureListener {
                Toast.makeText(applicationContext, it.message.toString(), Toast.LENGTH_LONG).show()
            }
        }else{
            Toast.makeText(applicationContext,"Empty", Toast.LENGTH_LONG).show()
        }
    }
}