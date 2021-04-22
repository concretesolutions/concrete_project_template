package br.com.concrete.plugins.projecttemplate.project_setup.src.app_package

import com.android.tools.idea.wizard.template.getMaterialComponentName
import com.android.tools.idea.wizard.template.escapeKotlinIdentifier
import br.com.concrete.plugins.projecttemplate.base.models.MainActivityData

fun mainActivityKt(
    mainActivityData: MainActivityData,
    useAndroidX: Boolean,
    useMaterial2: Boolean
) = """
package ${escapeKotlinIdentifier(mainActivityData.packageName)}

import android.os.Bundle
import ${getMaterialComponentName("android.support.design.widget.BottomNavigationView", useMaterial2)}
import ${getMaterialComponentName("android.support.v7.app.AppCompatActivity", useAndroidX)}
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController/Users/ruidersonoliveiradeandrade/AndroidStudioProjects/ConcreteProjectTemplate/src/main/kotlin/com/concrete/plugins/projecttemplate/project_setup/res/values/dimensXml.kt

class $mainActivityData.activityClass : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.${mainActivityData.layoutName})
        val navView: BottomNavigationView = findViewById(R.id.nav_view)

        val navController = findNavController(R.id.nav_host_fragment)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(setOf(
            R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications))
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }
}
"""
