package br.com.concrete.plugins.projecttemplate.core.base.models

import br.com.concrete.plugins.projecttemplate.core.base.extensions.createDirectory
import br.com.concrete.plugins.projecttemplate.core.base.extensions.createFile
import com.intellij.openapi.project.Project

abstract class PackageScope(
    val project: Project,
) {

    abstract val directory: String

    fun createDirectory(path: String) = project.createDirectory(path)

    fun createFile(name: String, rootDirectory: String = directory, content: String) = project.createFile(name, rootDirectory, content)

}