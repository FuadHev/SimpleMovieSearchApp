package com.example.mynewfabproject.di

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.database.Observable
import android.os.Build
import android.os.Build.VERSION_CODES.O
import dagger.hilt.android.HiltAndroidApp


@HiltAndroidApp
class HiltApplication : Application() {

    companion object {
        const val CHANNEL_ID = "0"
        const val CHANNEL_NAME = "Movie channel"
    }

    override fun onCreate() {
        super.onCreate()
        createNotificationChannel()
    }

    private fun createNotificationChannel() {

        if (Build.VERSION.SDK_INT >= O) {
            val channel = NotificationChannel(
                CHANNEL_ID,
                CHANNEL_NAME,
                NotificationManager.IMPORTANCE_DEFAULT
            )
            val manager=getSystemService(NotificationManager::class.java)
            manager.createNotificationChannel(channel)
        }
    }


}