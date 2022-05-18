package com.barmge.sampalemusicplayer

import android.app.ActivityManager
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.barmge.sampalemusicplayer.databinding.ActivityMainBinding
import com.barmge.sampalemusicplayer.services.MusicService

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)


        binding.startButton.setOnClickListener {
           Intent(this , MusicService::class.java).also {
               startService(it)
               binding.statusTextView.text = "Music Started"
           }
        }
        binding.stopButton.setOnClickListener {
            Intent(this , MusicService::class.java).also {
                stopService(it)
                binding.statusTextView.text = "Music Stopped"
            }
        }
    }


    /*private fun isMusicServiceRunning(mClass : Class<MusicService> ) : Boolean {

        val manager : ActivityManager = getSystemService(
            Context.ACTIVITY_SERVICE
        ) as ActivityManager

        for(service : ActivityManager.RunningServiceInfo in manager.getRunningServices(Integer.MAX_VALUE)){

            if(mClass.name.equals(service.service.className)){
                return true
            }
        }

        return false

    }*/
}