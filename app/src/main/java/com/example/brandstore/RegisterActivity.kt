package com.example.brandstore

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.example.brandstore.databinding.ActivityRegisterBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private lateinit var database: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = DataBindingUtil.setContentView<ActivityRegisterBinding>(this, R.layout.activity_register)

        auth = FirebaseAuth.getInstance()
        database = FirebaseDatabase.getInstance().getReference("Users")

        binding.btnRegister.setOnClickListener {
            val email = binding.edtEmailRegister.text.toString().trim()
            val password = binding.edtPasswordRegister.text.toString().trim()
            if (TextUtils.isEmpty(email)){
                binding.edtEmailRegister.error = "Enter Email"
                return@setOnClickListener
            }
            if (TextUtils.isEmpty(password)){
                binding.edtPasswordRegister.error = "Enter password"
                return@setOnClickListener
            }
            createUser(email, password)
        }

    }

    fun clickMeToLogin(view: View) {
        startActivity(Intent(this, LoginActivity::class.java))
        finish()
    }

    private fun createUser(email:String, password:String){
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) {task ->
                if (task.isSuccessful){
                    val user = auth.currentUser
                    val uid = user!!.uid
                    database.child(uid).setValue(email)
//                    val userID = database.push().key
//                    val user =User(userID,email)
//                    if (userID != null) {
//                        database.child(userID).setValue(user)
//                    }
                    startActivity(Intent(this, MainActivity::class.java))
                    Toast.makeText(this, "Welcome", Toast.LENGTH_SHORT).show()
                    finish()
                }else{
                    Toast.makeText(this, "Authentication failed.${task.exception}", Toast.LENGTH_SHORT).show()
                }
            }
    }
}
