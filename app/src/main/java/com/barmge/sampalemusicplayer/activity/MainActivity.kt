package com.barmge.sampalemusicplayer.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
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
               ContextCompat.startForegroundService(baseContext, it)
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

}