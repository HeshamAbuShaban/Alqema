package com.alqema.utils

import android.os.Handler
import android.os.Looper
import java.text.SimpleDateFormat
import java.util.*

class TimeUpdater(private val timeUpdateListener: TimeUpdateListener) {

    private val dateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
    private val handler = Handler(Looper.getMainLooper())

    private val updateTimeRunnable = object : Runnable {
        override fun run() {
            val currentTime = System.currentTimeMillis()
            val formattedTime = dateFormat.format(currentTime)
            timeUpdateListener.onTimeUpdated(formattedTime)
            handler.postDelayed(this, 1000) // Schedule the runnable again after 1 second
        }
    }

    fun startUpdatingTime() {
        handler.post(updateTimeRunnable)
    }

    fun stopUpdatingTime() {
        handler.removeCallbacks(updateTimeRunnable)
    }
    interface TimeUpdateListener {
        fun onTimeUpdated(time: String)
    }
}


