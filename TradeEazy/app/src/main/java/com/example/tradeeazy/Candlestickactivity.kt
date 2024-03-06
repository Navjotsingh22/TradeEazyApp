package com.example.tradeeazy

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.github.barteksc.pdfviewer.PDFView

class Candlestickactivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_candlestickactivity)

        val pdfView: PDFView = findViewById(R.id.pdfView)

        // Load the PDF file from assets
        pdfView.fromAsset("candlestickpatterns.pdf")
            .load()
    }
}
