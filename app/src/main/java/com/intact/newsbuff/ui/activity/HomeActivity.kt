package com.intact.newsbuff.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.intact.newsbuff.R
import com.intact.newsbuff.databinding.HomeActivityBinding

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: HomeActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = HomeActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initializeVariables()
    }

    private fun initializeVariables() {
        val navController = findNavController(R.id.nav_host_fragment)
        val appBarConfiguration = AppBarConfiguration(navController.graph, binding.drawerLayout)

        // setting up toolbar with navigation controller
        binding.toolBar.setupWithNavController(navController, appBarConfiguration)

        // set up navigation view with navigation controller
        binding.navView.setupWithNavController(navController)
    }
}
