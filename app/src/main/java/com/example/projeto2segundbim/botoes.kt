package com.example.projeto2segundbim

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat


class botoes : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout)

        val btn1: Button = findViewById(R.id.button2)
        val btn3: Button = findViewById(R.id.button3)

        if (Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.TIRAMISU) {
            if (ContextCompat.checkSelfPermission(
                    this,
                    android.Manifest.permission.POST_NOTIFICATIONS
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                ActivityCompat.requestPermissions(
                    this,
                    arrayOf(android.Manifest.permission.POST_NOTIFICATIONS),
                    101
                )

            }
        }

        btn1.setOnClickListener {
            val intent = Intent(this, desafios::class.java)
            startActivity(intent)
        }

        btn3.setOnClickListener {
            makeNotificacao()
        }

    }

    fun makeNotificacao() {
        val channelId = "CHANNEL_ID_NOTIFICATION"
        val builder = NotificationCompat.Builder(this, channelId)
            .setSmallIcon(R.drawable.ic_notifications)
            .setContentTitle("titulo")
            .setContentText("Lembre de fazer")
            .setAutoCancel(true)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)

        val intent = Intent(this, Notifi::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        intent.putExtra("data", "Lembre de fazer")

        val pendingIntent = PendingIntent.getActivity(
            applicationContext,
            0,
            intent,
            PendingIntent.FLAG_MUTABLE
        )
        builder.setContentIntent(pendingIntent)

        val notificationManager: NotificationManager =
            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            val notificationChannel: NotificationChannel? =
                notificationManager.getNotificationChannel(channelId)
            if (notificationChannel == null) {
                val importance = NotificationManager.IMPORTANCE_HIGH
                val newNotificationChannel =
                    NotificationChannel(channelId, "alguma descrição", importance)
                newNotificationChannel.lightColor = Color.GREEN
                newNotificationChannel.enableVibration(true)
                notificationManager.createNotificationChannel(newNotificationChannel)
            }
        }
        notificationManager.notify(0, builder.build())
    }
}
