package com.example.musicstoreapp

import android.app.Application
import com.example.musicstoreapp.di.AppContainer
import com.example.musicstoreapp.di.AppDataContainer

class MusicStoreApplication : Application() {
    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        container = AppDataContainer(this)
    }
}