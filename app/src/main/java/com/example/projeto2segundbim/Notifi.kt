package com.example.projeto2segundbim

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.projeto2segundbim.R

class Notifi: AppCompatActivity() {

    private lateinit var textView: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.notific)
        textView = findViewById(R.id.textviewData)
        val data = intent.getStringExtra("data")
        textView.setText(data)
    }
}