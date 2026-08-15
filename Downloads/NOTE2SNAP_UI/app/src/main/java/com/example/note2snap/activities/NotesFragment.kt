package com.example.note2snap.activities

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.note2snap.FolderRepository
import com.example.note2snap.R
import com.example.note2snap.adapter.FolderAdapter
import com.example.note2snap.adapter.NotesAdapter
import com.example.note2snap.model.Folder
import com.example.note2snap.model.Note
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.floatingactionbutton.FloatingActionButton

class NotesFragment : Fragment() {

    private val notesList = mutableListOf(
        Note("1", "OOP Discussion", "February 13, 2026", isStarred = true),
        Note("2", "Dijkstra's Algorithm", "March 17, 2026", isStarred = false)
    )

    private lateinit var folderAdapter: FolderAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_notes, container, false)

        val rvNotes = view.findViewById<RecyclerView>(R.id.rvNotes)
        val rvFolders = view.findViewById<RecyclerView>(R.id.rvFolders)
        val fabAdd = view.findViewById<FloatingActionButton>(R.id.fabAddNotes)

        // Notes Adapter
        rvNotes?.layoutManager = LinearLayoutManager(context)
        rvNotes?.adapter = NotesAdapter(notesList) { note ->
            val intent = Intent(context, PdfViewerActivity::class.java)
            intent.putExtra("TITLE", note.title)
            startActivity(intent)
        }

        // Folder Adapter with Edit & Delete actions
        folderAdapter = FolderAdapter(
            folderList = FolderRepository.foldersList,
            onItemClick = { folder ->
                // Handle opening folder notes
            },
            onEditClick = { folder ->
                showEditFolderDialog(folder)
            },
            onDeleteClick = { folder ->
                showDeleteFolderDialog(folder)
            }
        )

        rvFolders?.layoutManager = LinearLayoutManager(context)
        rvFolders?.adapter = folderAdapter

        fabAdd?.setOnClickListener { showBottomSheetMenu() }

        return view
    }

    override fun onResume() {
        super.onResume()
        if (::folderAdapter.isInitialized) {
            folderAdapter.notifyDataSetChanged()
        }
    }

    private fun showEditFolderDialog(folder: Folder) {
        val builder = AlertDialog.Builder(requireContext())
        builder.setTitle("Edit Folder Name")

        val input = EditText(requireContext())
        input.setText(folder.name)
        input.setSelection(folder.name.length)
        builder.setView(input)

        builder.setPositiveButton("Save") { dialog, _ ->
            val newName = input.text.toString().trim()
            if (newName.isNotEmpty()) {
                FolderRepository.updateFolder(folder.id, newName)
                folderAdapter.notifyDataSetChanged()
                Toast.makeText(context, "Folder updated", Toast.LENGTH_SHORT).show()
            }
            dialog.dismiss()
        }
        builder.setNegativeButton("Cancel") { dialog, _ -> dialog.cancel() }

        builder.show()
    }

    private fun showDeleteFolderDialog(folder: Folder) {
        AlertDialog.Builder(requireContext())
            .setTitle("Delete Folder")
            .setMessage("Are you sure you want to delete '${folder.name}'?")
            .setPositiveButton("Delete") { dialog, _ ->
                FolderRepository.deleteFolder(folder)
                folderAdapter.notifyDataSetChanged()
                Toast.makeText(context, "Folder deleted", Toast.LENGTH_SHORT).show()
                dialog.dismiss()
            }
            .setNegativeButton("Cancel") { dialog, _ -> dialog.dismiss() }
            .show()
    }

    private fun showBottomSheetMenu() {
        val dialog = BottomSheetDialog(requireContext())
        val dialogView = layoutInflater.inflate(R.layout.dialog_add_options, null)

        dialogView.findViewById<LinearLayout>(R.id.llOptionFolder)?.setOnClickListener {
            dialog.dismiss()
            startActivity(Intent(context, CreateFolderActivity::class.java))
        }

        dialogView.findViewById<LinearLayout>(R.id.llOptionNote)?.setOnClickListener {
            dialog.dismiss()
            (activity as? MainActivity)?.loadFragment(ScanFragment())
        }

        dialog.setContentView(dialogView)
        dialog.show()
    }
}