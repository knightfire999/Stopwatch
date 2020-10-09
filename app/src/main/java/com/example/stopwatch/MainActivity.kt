package com.example.stopwatch

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.os.SystemClock
import android.util.Log
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val TAG = "MainActivity"
    private val KEY_STARTED = "started or not"
    private val KEY_TIME_STOPPED = "time stopped"
    private val KEY_TEXT = "text when stopped"
    private val KEY_TIME_STARTED = "time started"
    private var timeElapsed = 0L
    private val KEY_TIME_ELAPSED = "elapsed time"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        chronometer_main_timer.visibility = View.INVISIBLE
        var timeWhenStopped:Long = 0

        //?. builds an if not null
        //?: is the elvis operator. What comes after is the default value if what comes before is null
        timeElapsed = savedInstanceState?.getLong(KEY_TIME_ELAPSED) ?: 0L

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

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart has been called")
    }
    override fun onRestart() {
        super.onRestart()
        Log.d(TAG, "onRestart has been called")
    }
    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume has been called")
    }
    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause has been called")
    }
    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onStop has been called")
    }
    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy has been called")
    }
    override fun onSaveInstanceState(outState: Bundle, outPersistentState: PersistableBundle) {
        super.onSaveInstanceState(outState, outPersistentState)
        Log.d(TAG, "onSavedInstanceState has been called")
        //in the Bundle, data is stored in key-value pairs
        //lots of key-value pairs here: JSON = javascript object notation (very commonly used for data storage online)
        //key: like a name tag you associate with the data
        //value: the actual data being stored
        outState.putLong(KEY_TIME_ELAPSED, timeElapsed)
        timeElapsed = chronometer_main_timer.getBase() - SystemClock.elapsedRealtime()
    }
}