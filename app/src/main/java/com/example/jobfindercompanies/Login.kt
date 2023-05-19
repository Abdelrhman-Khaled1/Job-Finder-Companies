package com.example.jobfindercompanies

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class Login : AppCompatActivity() {
    var mAuth : FirebaseAuth? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        mAuth = FirebaseAuth.getInstance()


        val btn_login = findViewById<Button>(R.id.btn_login)
        val btn_signup = findViewById<Button>(R.id.btn_signup)

        btn_login.setOnClickListener(View.OnClickListener {
            val email = (findViewById<EditText>(R.id.email)).text.toString()
            val password = (findViewById<EditText>(R.id.password)).text.toString()

            if(email.isNotEmpty() && password.isNotEmpty()) {
                mAuth?.signInWithEmailAndPassword(email, password)?.addOnCompleteListener {
                    if(it.isSuccessful){
                        val homeIntent = Intent(this,HomePage::class.java)




                        val database : FirebaseDatabase = FirebaseDatabase.getInstance()
                         val databaseReference = database.getReference("Companies")

                        val query = databaseReference.orderByChild("email").equalTo(email)
                        query.addListenerForSingleValueEvent(object : ValueEventListener {
                            override fun onDataChange(dataSnapshot: DataSnapshot) {
                                for (snapshot in dataSnapshot.children) {
                                    // Retrieve the company object from the snapshot
                                    val company = snapshot.getValue(Company::class.java)
                                    // Do something with the company object
                                    Globals.company = company
                                }
                            }

                            override fun onCancelled(databaseError: DatabaseError) {
                                // Handle the error
                            }
                        })











                        startActivity(homeIntent)
                        finish()
                    }else{
                        Toast.makeText(applicationContext, it.exception.toString(), Toast.LENGTH_LONG).show()
                    }
                }
            }else{
                Toast.makeText(applicationContext,"Empty", Toast.LENGTH_LONG).show()
            }
        })

        btn_signup.setOnClickListener(View.OnClickListener {
            val intent = Intent(this, SignUp1::class.java)
            startActivity(intent)
        })
    }
}