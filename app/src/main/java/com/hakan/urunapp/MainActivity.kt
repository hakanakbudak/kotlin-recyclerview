package com.hakan.urunapp

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import com.hakan.urunapp.databinding.ActivityMainBinding
import com.hakan.urunapp.databinding.ActivityMainBinding.inflate
import com.hakan.urunapp.databinding.FragmentFirstBinding.inflate
import com.hakan.urunapp.databinding.FragmentSecondBinding.inflate

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }


}
