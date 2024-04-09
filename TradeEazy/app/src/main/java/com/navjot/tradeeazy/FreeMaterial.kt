package com.navjot.tradeeazy

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.github.barteksc.pdfviewer.PDFView

class FreeMaterial : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_free_material)

        val pdfView: PDFView = findViewById(R.id.bookview)


        pdfView.fromAsset("logo.pdf")
            .load()

        findViewById<Button>(R.id.book1).setOnClickListener {
            pdfView.fromAsset("RichDadPoorDad.pdf")
                .load()
        }

        findViewById<Button>(R.id.book2).setOnClickListener {
            pdfView.fromAsset("PriceActionTradingGuide.pdf")
                .load()
        }

        findViewById<Button>(R.id.book3).setOnClickListener {
            pdfView.fromAsset("QuantativeTrading.pdf")
                .load()
        }

        findViewById<Button>(R.id.book4).setOnClickListener {
            pdfView.fromAsset("TheCompleteGuideToDayTrading.pdf")
                .load()
        }

        findViewById<Button>(R.id.book5).setOnClickListener {
            pdfView.fromAsset("OptionsMadeEasy.pdf")
                .load()
        }


        findViewById<Button>(R.id.book6).setOnClickListener {
            pdfView.fromAsset("NewTraderRichTrader.pdf")
                .load()
        }

        findViewById<Button>(R.id.book7).setOnClickListener {
            pdfView.fromAsset("OptionTradingStrategies.pdf")
                .load()
        }


        findViewById<Button>(R.id.book8).setOnClickListener {
            pdfView.fromAsset("TheCompleteGuidetoTrading.pdf")
                .load()
        }


        findViewById<Button>(R.id.book9).setOnClickListener {
            pdfView.fromAsset("TheNewTradingforaLiving.pdf")
                .load()
        }


        findViewById<Button>(R.id.book10).setOnClickListener {
            pdfView.fromAsset("BBTTradeBook.pdf")
                .load()
        }
    }
}
