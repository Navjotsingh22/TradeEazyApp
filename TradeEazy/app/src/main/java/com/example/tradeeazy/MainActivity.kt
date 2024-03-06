package com.example.tradeeazy

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider

class MainActivity : AppCompatActivity() {

    private lateinit var emailEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var loginButton: Button
    private lateinit var registerButton: Button
    private lateinit var fbBtn: ImageView
    private lateinit var gBtn: ImageView
    private lateinit var callbackManager: CallbackManager
    private lateinit var googleSignInClient: GoogleSignInClient
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initializeViews()

        firebaseAuth = FirebaseAuth.getInstance()

        // Configure Google Sign-In
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken("196362261492-7coeuppaoie20ifvs7fgl4nq78vobc7n.apps.googleusercontent.com")
            .requestEmail()
            .build()
        googleSignInClient = GoogleSignIn.getClient(this, gso)

        // Initialize Facebook callback manager
        callbackManager = CallbackManager.Factory.create()

        loginButton.setOnClickListener { loginUser() }
        registerButton.setOnClickListener { startActivity(Intent(this, RegisterActivity::class.java)) }
        fbBtn.setOnClickListener { signInWithFacebook() }
        gBtn.setOnClickListener { signInWithGoogle() }
    }

    private fun initializeViews() {
        emailEditText = findViewById(R.id.email)
        passwordEditText = findViewById(R.id.password)
        loginButton = findViewById(R.id.loginbtn)
        registerButton = findViewById(R.id.registerbtn)
        fbBtn = findViewById(R.id.fb_btn)
        gBtn = findViewById(R.id.g_btn)
    }

    private fun loginUser() {
        val email = emailEditText.text.toString().trim()
        val password = passwordEditText.text.toString().trim()

        if (email.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Please enter email and password", Toast.LENGTH_SHORT).show()
        } else {
            firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        updateUI(firebaseAuth.currentUser)
                        Toast.makeText(this@MainActivity, "Congratulations! You are logged in.", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(this, "Authentication Failed", Toast.LENGTH_SHORT).show()
                    }
                }
        }
    }

    private fun signInWithFacebook() {
        // Log out the user from Facebook to ensure they are prompted to log in again
        LoginManager.getInstance().logOut()

        // Proceed with the sign-in process
        LoginManager.getInstance().logInWithReadPermissions(this, listOf("email"))
        LoginManager.getInstance().registerCallback(callbackManager, object : FacebookCallback<LoginResult> {
            override fun onSuccess(loginResult: LoginResult) {
                Toast.makeText(this@MainActivity, "Congratulations! You are logged in.", Toast.LENGTH_SHORT).show()
                val firebaseUser = firebaseAuth.currentUser
                updateUI(firebaseUser)
                startActivity(Intent(this@MainActivity, WelcomeActivity::class.java))
            }

            override fun onCancel() {
                // Handle cancellation if needed
            }

            override fun onError(error: FacebookException) {
                Toast.makeText(this@MainActivity, "Facebook Sign In Failed", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun signInWithGoogle() {
        // Sign out the user from Google to ensure they are prompted to select an account again
        googleSignInClient.signOut().addOnCompleteListener(this) {
            // After sign-out, initiate the sign-in process
            val signInIntent = googleSignInClient.signInIntent
            startActivityForResult(signInIntent, RC_SIGN_IN)
        }
    }

    public override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == RC_SIGN_IN) {
            if (resultCode == Activity.RESULT_OK) {
                // User selected an account, proceed with sign-in
                val task = GoogleSignIn.getSignedInAccountFromIntent(data)
                try {
                    // Google Sign-In was successful, authenticate with Firebase
                    val account = task.getResult(ApiException::class.java)!!
                    firebaseAuthWithGoogle(account.idToken!!)
                    Toast.makeText(this@MainActivity, "Congratulations! You are logged in.", Toast.LENGTH_SHORT).show()
                } catch (e: ApiException) {
                    // Google Sign-In failed
                    Toast.makeText(this, "Google SignIn Failure: ${e.statusCode}", Toast.LENGTH_SHORT).show()
                }
            } else if (resultCode == Activity.RESULT_CANCELED) {
                // User canceled the account selection
                Toast.makeText(this@MainActivity, "Google SignIn Cancelled", Toast.LENGTH_SHORT).show()
            }
        }

        // Pass the activity result to the Facebook callback manager
        callbackManager.onActivityResult(requestCode, resultCode, data)
    }

    private fun firebaseAuthWithGoogle(idToken: String) {
        val credential = GoogleAuthProvider.getCredential(idToken, null)
        firebaseAuth.signInWithCredential(credential)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    updateUI(firebaseAuth.currentUser)
                } else {
                    Toast.makeText(this, "Google SignIn Failed", Toast.LENGTH_SHORT).show()
                }
            }
    }

    private fun updateUI(user: FirebaseUser?) {
        if (user != null) {
            startActivity(Intent(this, WelcomeActivity::class.java))
            finish() // Optional: to prevent the user from navigating back to the login screen using the back button
        }
    }

    companion object {
        private const val RC_SIGN_IN = 9001
    }
}
