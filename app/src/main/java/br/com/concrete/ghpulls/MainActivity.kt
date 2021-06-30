package br.com.concrete.ghpulls

import android.os.Build
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.Observer
import androidx.lifecycle.asLiveData
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import br.com.concrete.base.DataStoreManager
import br.com.concrete.ghpulls.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var dataStore: DataStoreManager

    @RequiresApi(Build.VERSION_CODES.Q)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        dataStore = DataStoreManager(this)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        setupNavBar()

        loadTheme()

        setAppTheme()
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
        lifecycleScope.launch {
            dataStore.saveTheme(theme)
        }
    }

    private fun loadTheme() {
        dataStore.actualTheme.asLiveData().observe(this, Observer { theme ->
            AppCompatDelegate.setDefaultNightMode(theme ?: 1)
        })
    }

    private fun setAppTheme() {
        if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES
        ) {
            setTheme(R.style.Theme_GithubPulls_night)
        } else {
            setTheme(R.style.Theme_GithubPulls)
        }
    }
}
