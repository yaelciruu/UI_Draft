package com.example.note2snap.model

data class ScanHistory(
    val id: String,
    val title: String,
    val date: String,
    val isSyncedLocal: Boolean = true
)