package com.bcas_briyan.zwallet.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bcas_briyan.zwallet.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}