package com.example.musicstoreapp.ui.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.musicstoreapp.ui.data.dao.ProductDao
import com.example.musicstoreapp.ui.data.model.ProductEntity

@Database(
    entities = [ProductEntity::class],
    version = 1
)
abstract class AppDatabase : RoomDatabase(){
    abstract fun productDao(): ProductDao
}