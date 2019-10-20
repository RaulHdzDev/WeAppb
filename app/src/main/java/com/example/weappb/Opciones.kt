package com.example.weappb

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button

class Opciones : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_opciones)

        val btnCubeta = findViewById<Button>(R.id.btnCubeta)
        btnCubeta.setOnClickListener(View.OnClickListener {
            startActivity(Intent(this@Opciones, Wecubeta::class.java))
        })

    }
}
