package br.com.concrete.plugins.projecttemplate.templates.feature_module.feature.src.main.kotlin.activity

import br.com.concrete.plugins.projecttemplate.templates.feature_module.feature.src.main.kotlin.MainKotlinScope

fun MainKotlinScope.setupActivity() = createFile(
    name = "${data.activityName}.kt",
    rootDirectory = "${directory}/${data.activityName}",
    content = """
package ${data.packageName}

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class ${data.activityName} : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.${data.activityName.toLowerCase()})
    }
    
}
""")