package com.example.note2snap.model

data class Folder(
    val id: String,
    val name: String,
    val colorHex: String,
    val details: String? = null,
    val noteCount: Int = 0
)