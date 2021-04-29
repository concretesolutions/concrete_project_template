package br.com.concrete.plugins.projecttemplate.templates

import com.android.tools.idea.wizard.template.Template
import com.android.tools.idea.wizard.template.WizardTemplateProvider
import br.com.concrete.plugins.projecttemplate.templates.feature_module.setupFeatureModuleTemplate

class WizardTemplateProviderImpl : WizardTemplateProvider() {

    override fun getTemplates(): List<Template> = listOf(
        setupFeatureModuleTemplate
    )

}