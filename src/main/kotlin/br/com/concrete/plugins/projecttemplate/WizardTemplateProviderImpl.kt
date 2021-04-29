package br.com.concrete.plugins.projecttemplate

import com.android.tools.idea.wizard.template.Template
import com.android.tools.idea.wizard.template.WizardTemplateProvider
import br.com.concrete.plugins.projecttemplate.templates.feature_module.setupFeatureModule

class WizardTemplateProviderImpl : WizardTemplateProvider() {

    override fun getTemplates(): List<Template> = listOf(
        setupFeatureModule
    )

}