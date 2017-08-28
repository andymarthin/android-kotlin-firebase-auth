package com.example.mcspicy.belajarkotlin

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class RegisterActivity : AppCompatActivity() {
    private lateinit var displayName: EditText
    private lateinit var status: EditText
    private lateinit var email: EditText
    private lateinit var password: EditText
    private lateinit var registerButton: Button
    private lateinit var auth:FirebaseAuth
    private lateinit var database:FirebaseDatabase
    private lateinit var dbRef: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        auth = FirebaseAuth.getInstance()
        database = FirebaseDatabase.getInstance()
        dbRef = database.reference

        displayName = findViewById(R.id.displayName) as EditText
        status = findViewById(R.id.status) as EditText
        email = findViewById(R.id.emailRegister) as EditText
        password = findViewById(R.id.passwordRegister) as EditText
        registerButton = findViewById(R.id.registerActionButton) as Button

        registerButton.setOnClickListener(){
            auth.createUserWithEmailAndPassword(email.text.toString(), password.text.toString()).
                    addOnCompleteListener { task: Task<AuthResult> ->
                        if (task.isSuccessful){
                            val userId = auth.currentUser?.uid
                            val registerRef = dbRef.child("user").child(userId)
                            val user = User(displayName.text.toString(), status.text.toString())
                            registerRef.setValue(user).addOnSuccessListener(){
                                val intent = Intent(this@RegisterActivity, LoginActivity::class.java)
                                startActivity(intent)
                            }
                        }
                    }
        }
    }
}
