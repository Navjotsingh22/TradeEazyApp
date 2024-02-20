package com.example.tradeeazy
import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult

class MainActivity : AppCompatActivity() {

    private lateinit var fbBtn: ImageView
    private lateinit var callbackManager: CallbackManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        callbackManager = CallbackManager.Factory.create()

        fbBtn = findViewById(R.id.fb_btn)

        fbBtn.setOnClickListener {
            // Initiating Facebook login process
            LoginManager.getInstance().logInWithReadPermissions(this@MainActivity, listOf("email"))
        }

        LoginManager.getInstance().registerCallback(callbackManager, object : FacebookCallback<LoginResult> {
            override fun onSuccess(loginResult: LoginResult) {
                // Handle successful login
                // Show congratulatory message
                Toast.makeText(this@MainActivity, "Congratulations! You are logged in.", Toast.LENGTH_SHORT).show()
            }

            override fun onCancel() {
                // Handle login cancellation
            }

            override fun onError(error: FacebookException) {
                // Handle login error
            }
        })
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        callbackManager.onActivityResult(requestCode, resultCode, data)
    }
}
