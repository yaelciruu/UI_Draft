package com.example.note2snap.activities

import android.os.Bundle
import android.widget.ImageButton
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.note2snap.R

class PdfViewerActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pdf_viewer)

        val title = intent.getStringExtra("TITLE") ?: "CS 212"
        findViewById<TextView>(R.id.tvPdfTitle).text = title
        findViewById<ImageButton>(R.id.btnPdfBack)?.setOnClickListener { finish() }

        findViewById<LinearLayout>(R.id.btnActionSaveNotes)?.setOnClickListener {
            Toast.makeText(this, "Saved to Notes", Toast.LENGTH_SHORT).show()
        }
        findViewById<LinearLayout>(R.id.btnActionDownload)?.setOnClickListener {
            Toast.makeText(this, "Downloading PDF...", Toast.LENGTH_SHORT).show()
        }
        findViewById<LinearLayout>(R.id.btnActionShare)?.setOnClickListener {
            Toast.makeText(this, "Sharing document...", Toast.LENGTH_SHORT).show()
        }
    }
}