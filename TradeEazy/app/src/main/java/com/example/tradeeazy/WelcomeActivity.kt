package com.example.tradeeazy
import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import com.google.firebase.auth.FirebaseAuth

class WelcomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)

        val drawerLayout = findViewById<DrawerLayout>(R.id.drawerlayout)
        val drawerButton = findViewById<ImageButton>(R.id.buttondrawer)
        val navigationView = findViewById<NavigationView>(R.id.navigationview)
        drawerButton.setOnClickListener {
            drawerLayout.openDrawer(GravityCompat.START)
        }

        navigationView.setNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.home -> {
                    Toast.makeText(this, "Home Tab is under development", Toast.LENGTH_SHORT).show()
                }
                R.id.candlestick -> {
                    startActivity(Intent(this, Candlestickactivity::class.java))
                }

                R.id.contact -> {
                    startActivity(Intent(this, ContactUs::class.java))
                }
                R.id.free -> {
                    startActivity(Intent(this, FreeMaterial::class.java))
                }
                R.id.lecture -> {
                    startActivity(Intent(this, VideoLecture::class.java))
                }
                R.id.signoutbtn ->  signOut()
            }

            true
        }
    }
    private fun signOut() {
        FirebaseAuth.getInstance().signOut()
        startActivity(Intent(this, MainActivity::class.java)) // Redirect to login screen
        finish() // Close the WelcomeActivity
    }

}
