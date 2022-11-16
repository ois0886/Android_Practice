package com.example.mysampleapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {

        auth = Firebase.auth

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Toast.makeText(this, auth.currentUser?.uid.toString(), Toast.LENGTH_SHORT).show()
        val joinBtnClicked = findViewById<Button>(R.id.joinBtn)
        val logoutBtnClicked = findViewById<Button>(R.id.logoutBtn)
        val logInBtnClicked = findViewById<Button>(R.id.logInBtn)
        joinBtnClicked.setOnClickListener {
            val email = findViewById<EditText>(R.id.emailArea).toString()
            val password = findViewById<EditText>(R.id.passwordArea).toString()

            auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        Toast.makeText(this, "ok", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(this, "no", Toast.LENGTH_SHORT).show()
                    }
                }

        }

        logoutBtnClicked.setOnClickListener {
            auth.signOut()
            Toast.makeText(this, auth.currentUser?.uid.toString(), Toast.LENGTH_SHORT).show()
        }

        logInBtnClicked.setOnClickListener {

        }
    }
}