package com.example.brandstore

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.example.brandstore.databinding.ActivityLoginBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {
    lateinit var auth  : FirebaseAuth
    lateinit var firebaseUser: FirebaseUser

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = DataBindingUtil.setContentView<ActivityLoginBinding>(this, R.layout.activity_login)

        auth = FirebaseAuth.getInstance()

        binding.btnLogin.setOnClickListener {
            val email = binding.edtEmail.text.toString().trim()
            val password =  binding.edtPassword.text.toString().trim()
            if(TextUtils.isEmpty(email)){
                binding.edtEmail.error = " Enter Email"
                return@setOnClickListener
            }

            if(TextUtils.isEmpty(password)){
                binding.edtPassword.error = " Enter Password"
                return@setOnClickListener
            }
            loginUser(email, password)
        }
        val currentUser = FirebaseAuth.getInstance().currentUser
        if(currentUser != null){
            val intent = Intent(this , MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    fun clickMeRegister(view: View) {
        startActivity(Intent(this, RegisterActivity::class.java))
        finish()
    }

    private fun loginUser(email:String, password:String){

        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) {task ->
                if (task.isSuccessful) {
                    startActivity(Intent(this, MainActivity::class.java))
                    Toast.makeText(this, "Welcome", Toast.LENGTH_SHORT).show()
                    finish()
                } else {

                    Toast.makeText(this, "Authentication failed.${task.exception}", Toast.LENGTH_SHORT).show()

                }
            }
    }

}
