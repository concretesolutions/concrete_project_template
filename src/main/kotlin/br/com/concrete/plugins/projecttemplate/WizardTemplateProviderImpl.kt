package br.com.concrete.plugins.projecttemplate

import com.android.tools.idea.wizard.template.Template
import com.android.tools.idea.wizard.template.WizardTemplateProvider
import br.com.concrete.plugins.projecttemplate.project_setup.setupTemplate

class WizardTemplateProviderImpl : WizardTemplateProvider() {

    override fun getTemplates(): List<Template> = listOf(setupTemplate)

}