package com.navjot.tradeeazy

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class
ContactUs : AppCompatActivity() {

    private lateinit var asubject: EditText
    private lateinit var amessage: EditText
    private lateinit var sendButton: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contactus)

        asubject = findViewById(R.id.subject)
        amessage = findViewById(R.id.editTextMessage)

        sendButton= findViewById(R.id.submitButton)

        sendButton.setOnClickListener {
            // Retrieve form data
            val subject = asubject.text.toString().trim()
            val message = amessage.text.toString().trim()

            // Validate form data
            if (subject.isEmpty()) {
                Toast.makeText(this@ContactUs, "Please enter a subject", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (message.isEmpty()) {
                Toast.makeText(this@ContactUs, "Please enter a message", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Compose email
            val email = "nav88901@gmail.com"
            val emailIntent = Intent(Intent.ACTION_SENDTO).apply {
                data = Uri.parse("mailto:")
                putExtra(Intent.EXTRA_EMAIL, arrayOf(email))
                putExtra(Intent.EXTRA_SUBJECT, subject)
                putExtra(Intent.EXTRA_TEXT, message)
            }

            try {
                startActivity(Intent.createChooser(emailIntent, "Send email..."))
            } catch (e: Exception) {
                Toast.makeText(this@ContactUs, "Error: ${e.message}", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
