package com.nsztta.toohelper.ui.screen

import android.os.Bundle
import android.util.Log
import android.webkit.WebChromeClient
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.nsztta.toohelper.R
import com.nsztta.toohelper.databinding.ActivityPdfViewerBinding

class PdfViewerActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPdfViewerBinding
    private lateinit var webView: WebView
    private lateinit var pdfUrl:String
    private lateinit var pdfName:String


    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityPdfViewerBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)


        webView = findViewById(R.id.webView)
        pdfUrl = intent.getStringExtra("pdfUrl").toString()
        pdfName = intent.getStringExtra("pdfName").toString()

        // Replace "YOUR_PDF_URL" with the actual PDF download URL


        // Enable JavaScript (if needed)
        val webSettings: WebSettings = webView.settings
        webSettings.javaScriptEnabled = true

        // Set WebView clients
        webView.webViewClient = WebViewClient()
        webView.webChromeClient = WebChromeClient()
        Log.d("url", "Hellorome")

        // Load the PDF
        webView.loadUrl(pdfUrl)


    }
}