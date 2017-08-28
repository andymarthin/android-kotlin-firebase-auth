package com.example.mcspicy.belajarkotlin

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var textView: TextView
    private lateinit var auth: FirebaseAuth
    private lateinit var database: FirebaseDatabase
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        auth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();

        isLogin()
    }

    private fun isLogin(){
        if(auth.currentUser == null){
           val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
    }


}
