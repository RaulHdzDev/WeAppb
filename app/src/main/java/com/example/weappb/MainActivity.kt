package com.example.weappb

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.content.Intent
import com.google.firebase.auth.FirebaseAuth



class MainActivity : AppCompatActivity() {

    private var mDelayHandler: Handler? = null
    private val SPLASH_DELAY: Long = 2630 //3 seconds

    internal val mRunnable: Runnable = Runnable {
        if (!isFinishing) {
            checaSesion()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mDelayHandler = Handler()

        mDelayHandler!!.postDelayed(mRunnable, SPLASH_DELAY)

    }

    fun checaSesion(){
        val auth = FirebaseAuth.getInstance()
        val authListener = FirebaseAuth.AuthStateListener { firebaseAuth ->
            val firebaseUser = firebaseAuth.currentUser
            if (firebaseUser != null) {
                startActivity(Intent(this@MainActivity, Opciones::class.java))
            } else {
                startActivity(Intent(this@MainActivity, Iniciosesion::class.java))
            }
            finish()
        }
        auth.addAuthStateListener(authListener);
    }

    public override fun onDestroy() {

        if (mDelayHandler != null) {
            mDelayHandler!!.removeCallbacks(mRunnable)
        }
        super.onDestroy()
    }
}
