package com.example.musicstoreapp.ui.data.database

import android.content.Context
import androidx.room.Room

object DatabaseProvider {
    fun getDatabase(context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "music_store"
        ).build()
    }
}
