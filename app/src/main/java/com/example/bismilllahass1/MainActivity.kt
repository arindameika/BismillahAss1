package com.example.bismilllahass1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.example.bismilllahass1.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
        //buat variabel binding
        val binding: ActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        //set navController
        navController = this.findNavController(R.id.navigationHome)
        NavigationUI.setupActionBarWithNavController(this, navController)
    }

    //onsupportNavigateUp
    override fun onSupportNavigateUp(): Boolean {
        navController = this.findNavController(R.id.navigationHome)
        return navController.navigateUp()
    }
}
