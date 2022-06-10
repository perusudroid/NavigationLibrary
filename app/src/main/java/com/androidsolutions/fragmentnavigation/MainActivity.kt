package com.androidsolutions.fragmentnavigation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupActionBarWithNavController
import com.androidsolutions.fragmentnavigation.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private val navFrag by lazy { supportFragmentManager.findFragmentById(R.id.container) as NavHostFragment }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setupActionBarWithNavController(navFrag.navController)
    }

    override fun onSupportNavigateUp(): Boolean {
        return navFrag.navController.navigateUp() || super.onSupportNavigateUp()
    }
}