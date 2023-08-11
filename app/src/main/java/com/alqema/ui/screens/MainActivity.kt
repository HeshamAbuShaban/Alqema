package com.alqema.ui.screens

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.alqema.R
import com.alqema.databinding.ActivityMainBinding
import com.google.android.material.datepicker.MaterialTextInputPicker
import com.google.android.material.textfield.TextInputEditText

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }

    private fun setupListeners(){

    }
}