package com.example.note2snap.activities

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.note2snap.R
import com.example.note2snap.adapter.HistoryAdapter
import com.example.note2snap.model.ScanHistory

class HistoryFragment : Fragment() {

    private val historyData = listOf(
        ScanHistory("1", "Scan 5", "March 17, 2026", isSyncedLocal = true),
        ScanHistory("2", "Scan 4", "April 5, 2026", isSyncedLocal = true),
        ScanHistory("3", "Scan 3", "December 7, 2025", isSyncedLocal = true),
        ScanHistory("4", "Scan 2", "November 20, 2025", isSyncedLocal = true),
        ScanHistory("5", "Scan 1", "October 10, 2025", isSyncedLocal = true)
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_history, container, false)

        val rvHistory = view.findViewById<RecyclerView>(R.id.rvHistory)
        val llEmptyHistory = view.findViewById<LinearLayout>(R.id.llEmptyHistory)

        if (historyData.isEmpty()) {
            llEmptyHistory?.visibility = View.VISIBLE
            rvHistory?.visibility = View.GONE
        } else {
            llEmptyHistory?.visibility = View.GONE
            rvHistory?.visibility = View.VISIBLE
            rvHistory?.layoutManager = LinearLayoutManager(context)
            rvHistory?.adapter = HistoryAdapter(historyData) { item ->
                val intent = Intent(context, PdfViewerActivity::class.java)
                intent.putExtra("TITLE", item.title)
                startActivity(intent)
            }
        }

        return view
    }
}