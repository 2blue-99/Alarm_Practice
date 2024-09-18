package com.blue.alarm_practice.alarm

import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.IBinder
import android.util.Log
import com.blue.alarm_practice.MainActivity

class AlarmService: Service() {
    init {
        Log.e("TAG", "나 만들어짐: ", )
    }

    override fun onCreate() {
        Log.e("TAG", "onCreate: ")
        super.onCreate()

//        val activityIntent = Intent(context, MainActivity::class.java).apply {
//            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
//        }
//
//        context.startActivity(activityIntent)
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.e("TAG", "onStartCommand: ")
//        when(intent?.action){
//            ACTION_START
//        }
        return START_STICKY
    }

    override fun onBind(intent: Intent?): IBinder? {
        Log.e("TAG", "onBind: ")
        throw UnsupportedOperationException("Not yet Implemented")
    }
}