package br.com.concrete.ghpulls

import android.content.Context
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import br.com.concrete.ghpulls.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        setupNavBar()

        loadTheme()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = MenuInflater(this)
        inflater.inflate(R.menu.theme_selection_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        val theme = when (item.itemId) {
            R.id.theme_default -> AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM
            R.id.theme_Light -> AppCompatDelegate.MODE_NIGHT_NO
            R.id.theme_Dark -> AppCompatDelegate.MODE_NIGHT_YES
            else -> null
        }
        theme?.let {
            saveTheme(it)
            AppCompatDelegate.setDefaultNightMode(it)
        }
        val check = !item.isChecked
        item.isChecked = check
        return true
    }

    private fun setupNavBar() {
        val navView: BottomNavigationView = binding.navView
        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment_activity_main) as NavHostFragment
        val navController = navHostFragment.navController
        val appBarConfiguration = AppBarConfiguration(
            setOf(R.id.navigation_home, R.id.navigation_search)
        )

        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }

    private fun saveTheme(theme: Int) {
        val sharedPref = this.getPreferences(Context.MODE_PRIVATE) ?: return
        with(sharedPref.edit()) {
            putInt("ActualTheme", theme)
            commit()
        }
    }

    private fun loadTheme() {
        val sharedPref = this.getPreferences(Context.MODE_PRIVATE)
        val defaultValue = 1
        val theme = sharedPref.getInt("ActualTheme", defaultValue)
        AppCompatDelegate.setDefaultNightMode(theme)
    }
}
