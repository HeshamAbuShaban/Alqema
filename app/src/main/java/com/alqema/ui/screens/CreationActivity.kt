package com.alqema.ui.screens

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.alqema.R
import com.alqema.databinding.ActivityCreationBinding

class CreationActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCreationBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCreationBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }
}