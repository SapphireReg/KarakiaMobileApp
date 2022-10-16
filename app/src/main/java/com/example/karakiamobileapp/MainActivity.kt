package com.example.karakiamobileapp

import android.content.Context
import android.content.res.AssetManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import dagger.hilt.android.AndroidEntryPoint
import java.io.InputStream


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

          val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment //access nav controller on mainactivity oncreate
        navController = navHostFragment.findNavController()

        val appBarConfiguration = AppBarConfiguration(navController.graph)
        setupActionBarWithNavController(navController, appBarConfiguration)

        }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
        }


    class CustomClass(context: Context) {
        private var assetManager : AssetManager = context.assets

        fun readTextFileToString(fileName: String) : String {

            val inputStream : InputStream = assetManager.open(fileName)
            var fileText = ""
            inputStream.bufferedReader().forEachLine { fileText += it + "\n" }
            return fileText
        }
    }
}
//class MyActivity: AppCompatActivity() {
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//    }
//
//    fun readTextFileToString(fileName: String): String {
//        val inputStream: InputStream = assets.open(fileName)
//        var fileText = ""
//        inputStream.bufferedReader().forEachLine { fileText += it + "\n" }
//        return fileText
//    }
//}
