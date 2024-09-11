package com.blue.alarm_practice.alarm

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log

class AlarmService: Service() {

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.e("TAG", "onStartCommand: ")
        return START_STICKY
    }

    override fun onBind(intent: Intent?): IBinder? {
        Log.e("TAG", "onBind: ")
        throw UnsupportedOperationException("Not yet Implemented")
    }
}