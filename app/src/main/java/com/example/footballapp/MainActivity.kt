package com.example.footballapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.footballapp.databinding.ActivityMainBinding
import com.example.footballapp.ui.fixtures.FixturesFragment
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportFragmentManager
            .beginTransaction()
            .add(binding.container.id, FixturesFragment(), "fixtures_fragment_TAG")
            .commit()
    }
}