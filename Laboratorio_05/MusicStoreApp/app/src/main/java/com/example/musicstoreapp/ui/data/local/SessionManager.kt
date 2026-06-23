package com.example.musicstoreapp.ui.data.local

import android.content.Context

interface SessionManager {
    fun saveSession(user: UserEntity)
    fun getSession(): UserEntity?
    fun clearSession()
    fun isLoggedIn(): Boolean
}

class SessionManagerImpl(private val context: Context) : SessionManager {
    override fun saveSession(user: UserEntity) {
        UserStorage.saveSession(context, user)
    }

    override fun getSession(): UserEntity? {
        return UserStorage.getSession(context)
    }

    override fun clearSession() {
        UserStorage.clearSession(context)
    }

    override fun isLoggedIn(): Boolean {
        return getSession() != null
    }
}