package com.example.note2snap.model

data class Note(
    val id: String,
    val title: String,
    val date: String,
    val folderId: String? = null,
    val isStarred: Boolean = false,
    val pdfPath: String? = null
)