package com.example.karakiamobileapp

import android.content.Context
import android.content.res.AssetManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.core.content.edit
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.onNavDestinationSelected
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.karakiamobileapp.ui.FirstOpenDialogFragment
import dagger.hilt.android.AndroidEntryPoint
import java.io.InputStream
import java.util.zip.Inflater


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

        checkFirstRun()
        }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.options_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return if (item.itemId == R.id.aboutUs) {
            val action = NavgraphDirections.actionGlobalAboutUsFragment()
            navController.navigate(action)
            true
        } else if (item.itemId == R.id.getting_started) {
            val action = NavgraphDirections.actionGlobalGettingStartedFragment()
            navController.navigate(action)
            true
        } else {
            item.onNavDestinationSelected(navController) || super.onOptionsItemSelected(item)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
        }

    private fun checkFirstRun() {
        val isFirstRun = getSharedPreferences("PREFERENCE", MODE_PRIVATE).getBoolean("isFirstRun", true)
        if (isFirstRun) {

            //dialog box here
            val dialogbox = FirstOpenDialogFragment()
            dialogbox.show(supportFragmentManager, "first use disclaimer")

            getSharedPreferences("PREFERENCE", MODE_PRIVATE).edit {
                putBoolean("isFirstRun", false)
            }
        }
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
