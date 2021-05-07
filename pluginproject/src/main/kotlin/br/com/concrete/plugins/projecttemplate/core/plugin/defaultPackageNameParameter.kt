package br.com.concrete.plugins.projecttemplate.core.plugin

import com.android.tools.idea.wizard.template.Constraint
import com.android.tools.idea.wizard.template.stringParameter

val defaultPackageNameParameter get() = stringParameter {
    name = "Package name"
    visible = { !isNewModule }
    default = "br.com.concrete.app"
    constraints = listOf(Constraint.PACKAGE)
    suggest = { packageName }
}