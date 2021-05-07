package br.com.concrete.plugins.projecttemplate.core.plugin.services

import br.com.concrete.plugins.projecttemplate.core.plugin.MyBundle
import com.intellij.openapi.project.Project

class MyProjectService(project: Project) {

    init {
        println(MyBundle.message("projectService", project.name))
    }

}