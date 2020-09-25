package com.example.stopwatch

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.SystemClock
import android.util.Log
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val TAG = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        chronometer_main_timer.visibility = View.INVISIBLE
        var timeWhenStopped:Long = 0

        button_main_start.setOnClickListener {
            chronometer_main_timer.visibility = View.VISIBLE
            button_main_start.visibility = View.INVISIBLE
            button_main_stop.visibility = View.VISIBLE
            button_main_reset.visibility = View.VISIBLE
            chronometer_main_timer.base = SystemClock.elapsedRealtime() + timeWhenStopped
            chronometer_main_timer.start()
        }

        button_main_stop.setOnClickListener {
            timeWhenStopped = chronometer_main_timer.base-SystemClock.elapsedRealtime()
            chronometer_main_timer.stop()
            button_main_start.visibility = View.VISIBLE
            button_main_reset.visibility = View.VISIBLE
        }

        button_main_reset.setOnClickListener {
            chronometer_main_timer.base = SystemClock.elapsedRealtime()
            timeWhenStopped = 0
            chronometer_main_timer.stop()
        }
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause has been called")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d(TAG, "onRestart has been called")
    }
}