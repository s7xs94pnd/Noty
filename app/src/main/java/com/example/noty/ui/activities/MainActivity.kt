package com.example.noty.ui.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import com.example.noty.App
import com.example.noty.R
import com.example.noty.ui.utils.PreferenceHelper

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_container) as NavHostFragment
        val navController =navHostFragment.navController
        val navGraph = navController.navInflater.inflate(R.navigation.nav_graph)
        val sharedPreferences = PreferenceHelper()
        sharedPreferences.helper(this)
        if(sharedPreferences.isOnboardShown){
            navGraph.setStartDestination(R.id.homeNotyFragment)
        }
        else {
            navGraph.setStartDestination(R.id.onBoardFragment)
        }
        navController.graph = navGraph
    }
}