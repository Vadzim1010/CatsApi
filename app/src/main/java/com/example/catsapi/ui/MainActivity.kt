package com.example.catsapi.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.example.catsapi.R

class MainActivity : AppCompatActivity() {

    private var _navController: NavController? = null
    private val navController get() = _navController!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        _navController = findNavController(R.id.nav_host)
    }

    override fun onSupportNavigateUp(): Boolean {
        navController.popBackStack()
        return true
    }
}