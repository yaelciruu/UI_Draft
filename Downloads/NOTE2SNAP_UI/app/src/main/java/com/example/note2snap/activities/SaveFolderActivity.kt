package com.example.note2snap.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.note2snap.R
import com.google.android.material.floatingactionbutton.FloatingActionButton

class SaveFolderActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_save_folder)

        val btnSave = findViewById<Button>(R.id.btnSaveNote)
        val fabAddFolder = findViewById<FloatingActionButton>(R.id.fabAddFolderInSave)
        val radioGroup = findViewById<RadioGroup>(R.id.rgFolders)

        btnSave?.setOnClickListener {
            val selectedId = radioGroup.checkedRadioButtonId
            if (selectedId != -1) {
                val radioButton = findViewById<RadioButton>(selectedId)
                Toast.makeText(this, "Saved to \${radioButton.text}", Toast.LENGTH_SHORT).show()
            }
            val intent = Intent(this, PdfViewerActivity::class.java)
            intent.putExtra("TITLE", "CS 212 - Analysis Note")
            startActivity(intent)
            finish()
        }

        fabAddFolder?.setOnClickListener {
            startActivity(Intent(this, CreateFolderActivity::class.java))
        }
    }
}