package com.example.sameera.musicsensor

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.media.MediaPlayer
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast

class MainActivity : AppCompatActivity(), SensorEventListener {

    var sensor: Sensor? = null
    var sensorManager: SensorManager? = null
    var isRunning = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        //sensor manager never be a null !!
        sensor = sensorManager!!.getDefaultSensor(Sensor.TYPE_LIGHT)
    }

    //sensor have to register one time and unregister another time
    //register sensor at onresume
    override fun onResume() {
        super.onResume()
        sensorManager!!.registerListener(this, sensor, SensorManager.SENSOR_DELAY_NORMAL)
    }

    //unregister on onPause method

    override fun onPause() {
        super.onPause()

        sensorManager!!.unregisterListener(this)
    }


    override fun onSensorChanged(event: SensorEvent?) {
//if onsensor changes there is event.event have values 30 is bright

        if (event!!.values[0] > 30 && isRunning == false) {
            isRunning = true
            //play using media player
            try {
                var mp = MediaPlayer()
                mp.setDataSource("https://s0.vocaroo.com/media/download_temp/Vocaroo_s0DCOpaqA5H0.mp3")
                mp.prepare()
                mp.start()
            } catch (ex: Exception) {
                Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show()
            }

        } else {

        }
    }

    //not using
    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {

    }

}
