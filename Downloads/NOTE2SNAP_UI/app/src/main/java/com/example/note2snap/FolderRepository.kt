package com.example.note2snap

import com.example.note2snap.model.Folder

object FolderRepository {
    val foldersList = mutableListOf(
        Folder("1", "CS101", "#AFC4F6", noteCount = 5),
        Folder("2", "CS262", "#5A7FDB", noteCount = 3),
        Folder("3", "ALG101", "#C7EAC2", noteCount = 8)
    )

    fun addFolder(name: String, colorHex: String = "#AFC4F6") {
        val newId = (foldersList.size + 1).toString()
        foldersList.add(Folder(newId, name, colorHex, noteCount = 0))
    }

    fun deleteFolder(folder: Folder) {
        foldersList.remove(folder)
    }

    fun updateFolder(folderId: String, newName: String) {
        val index = foldersList.indexOfFirst { it.id == folderId }
        if (index != -1) {
            val oldFolder = foldersList[index]
            foldersList[index] = oldFolder.copy(name = newName)
        }
    }
}