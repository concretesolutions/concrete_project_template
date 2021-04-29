package br.com.concrete.plugins.projecttemplate.core.base.extensions

import com.intellij.openapi.project.Project
import com.intellij.psi.PsiDirectory
import com.intellij.psi.PsiFileFactory
import com.intellij.psi.impl.file.PsiDirectoryFactory
import org.jetbrains.kotlin.idea.KotlinLanguage

fun Project.getRootDirectory() = PsiDirectoryFactory.getInstance(this).createDirectory(this.baseDir)

fun Project.createDirectory(path: String): PsiDirectory = if(path.isBlank()){
    getRootDirectory()
}else{

    var result = getRootDirectory()
    path.split(".", "/").forEach {
        if(it.isNotBlank()){
            result = result.findSubdirectory(it) ?: result.createSubdirectory(it)
        }
    }

    result
}

fun Project.createFile(name: String, directory: String, content: String){
    createDirectory(directory).add(
        PsiFileFactory
            .getInstance(this)
            .createFileFromText(
                name,
                KotlinLanguage.INSTANCE,
                content.trimIndent()
            )
    )
}