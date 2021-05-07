package br.com.concrete.plugins.projecttemplate.templates.feature_module.feature.src.main.kotlin.activity

import br.com.concrete.plugins.projecttemplate.templates.feature_module.feature.src.main.kotlin.MainKotlinScope

fun MainKotlinScope.setupViewModel() = createFile(
    name = "${data.activityName}ViewModel.kt",
    rootDirectory = "${directory}/${data.activityName.toLowerCase()}",
    content = """
package ${data.packageName}

import androidx.lifecycle.ViewModel

class ${data.activityName}ViewModel : ViewModel(){

}
""")