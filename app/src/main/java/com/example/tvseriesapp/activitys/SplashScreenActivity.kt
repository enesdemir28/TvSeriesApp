package com.example.tvseriesapp.activitys

import android.annotation.SuppressLint
import android.content.Intent
import android.net.ConnectivityManager
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.example.tvseriesapp.R


@SuppressLint("CustomSplashScreen")
class SplashScreenActivity : AppCompatActivity() {

    private val splashTimeOut: Long = 4000 // 4 saniye
    private lateinit var connectivityManager: ConnectivityManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        connectivityManager = getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager

        Handler().postDelayed({
            // MainActivity'e geçiş kodu burada olmalı
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish() // Bu aktiviteyi kapatmak, geri butonuna basıldığında bu aktivitenin tekrar açılmasını engeller
        }, splashTimeOut)
    }
}