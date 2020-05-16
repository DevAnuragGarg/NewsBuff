package com.intact.newsbuff.ui.activity

import android.os.Bundle
import android.view.View
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

        // u can set the menu items or the nav graph depending upon
        // which screen you want to make the top-level destination
        val appBarConfiguration = AppBarConfiguration(
            setOf(R.id.homeFragment, R.id.favouriteNewsFragment),
            binding.drawerLayout
        )

        // setting up toolbar with navigation controller
        binding.toolBar.setupWithNavController(navController, appBarConfiguration)

        // set up navigation view with navigation controller
        binding.navView.setupWithNavController(navController)

        // set up bottom navigation view with navigation controller
        binding.bottomNavigationView.setupWithNavController(navController)

        // showing/hiding the bottom navigation as it is outside the scope
        // BottomNavigationBar—live outside of the NavHost
        navController.addOnDestinationChangedListener { _, destination, _ ->
            if (destination.id != R.id.homeFragment && destination.id != R.id.favouriteNewsFragment) {
                binding.bottomNavigationView.visibility = View.GONE
            } else {
                binding.bottomNavigationView.visibility = View.VISIBLE
            }
        }
    }
}
