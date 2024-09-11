package com.blue.alarm_practice.alarm

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import android.util.Log
import androidx.core.app.NotificationCompat
import com.blue.alarm_practice.MainActivity
import com.blue.alarm_practice.R
import java.net.IDN

class AlarmReceiver: BroadcastReceiver() {

    private lateinit var manager: NotificationManager
    private lateinit var builder: NotificationCompat.Builder

    companion object {
        const val MY_ID = "MYID"
        const val MY_NAME = "MYNAME"
    }

    override fun onReceive(context: Context?, intent: Intent?) {
        Log.e("TAG", "onReceive: 나 받았음")
        manager = context?.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            manager.createNotificationChannel(
                NotificationChannel(
                    MY_ID,
                    MY_NAME,
                    NotificationManager.IMPORTANCE_HIGH
                )
            )
        }

        builder = NotificationCompat.Builder(context, MY_ID)

        val intent2 = Intent(context, MainActivity::class.java)
        val requestCode = intent?.extras!!.getInt("alarm_rqCode")
        val title = intent.extras!!.getString("content")

        val pendingIntent = if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.S){
            PendingIntent.getActivity(context, requestCode, intent2, PendingIntent.FLAG_IMMUTABLE)
        } else {
            PendingIntent.getActivity(context, requestCode, intent2, PendingIntent.FLAG_UPDATE_CURRENT)
        }

        val notification = builder.setContentTitle(title)
            .setContentText("SCHEDULE MANAGER")
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setAutoCancel(true)
            .setContentIntent(pendingIntent)
            .build()

        manager.notify(1, notification)
    }
}