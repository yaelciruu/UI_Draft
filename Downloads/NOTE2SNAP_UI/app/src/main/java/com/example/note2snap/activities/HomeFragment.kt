package com.example.note2snap.activities

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import com.example.note2snap.R
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class HomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        val tvGreeting = view.findViewById<TextView>(R.id.tvGreeting)
        val tvDate = view.findViewById<TextView>(R.id.tvDate)
        val cardScan = view.findViewById<CardView>(R.id.cardScan)
        val cardNotes = view.findViewById<CardView>(R.id.cardNotes)
        val cardRecentNote = view.findViewById<View>(R.id.cardRecentNote)

        // Dynamically update greeting and current date
        updateHeaderAndDate(tvGreeting, tvDate)

        // Swapping to Scan Tab and highlighting Scan icon
        cardScan?.setOnClickListener {
            (activity as? MainActivity)?.selectTab(R.id.nav_scan)
        }

        // Swapping to Notes Tab and highlighting Notes icon
        cardNotes?.setOnClickListener {
            (activity as? MainActivity)?.selectTab(R.id.nav_notes)
        }

        cardRecentNote?.setOnClickListener {
            val intent = Intent(context, PdfViewerActivity::class.java)
            intent.putExtra("TITLE", "CS 212 - Simulation")
            startActivity(intent)
        }

        return view
    }

    private fun updateHeaderAndDate(tvGreeting: TextView?, tvDate: TextView?) {
        // Set real-time date (e.g., "Friday, August 7")
        val dateFormat = SimpleDateFormat("EEEE, MMMM d", Locale.getDefault())
        val currentDate = dateFormat.format(Calendar.getInstance().time)
        tvDate?.text = currentDate

        // Set dynamic greeting based on time of day
        val hour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY)
        tvGreeting?.text = when (hour) {
            in 5..11 -> "Good morning! Ready to Study?"
            in 12..16 -> "Good afternoon! Ready to Study?"
            in 17..21 -> "Good evening! Ready to Study?"
            else -> "Late night study session?"
        }
    }
}