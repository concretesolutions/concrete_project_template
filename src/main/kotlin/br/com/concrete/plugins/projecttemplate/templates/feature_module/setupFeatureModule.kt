package br.com.concrete.plugins.projecttemplate.templates.feature_module

import br.com.concrete.plugins.projecttemplate.core.base.enums.DependencyInjectionEnum
import br.com.concrete.plugins.projecttemplate.core.plugin.defaultPackageNameParameter
import br.com.concrete.plugins.projecttemplate.core.plugin.listeners.MyProjectManagerListener.Companion.projectInstance
import br.com.concrete.plugins.projecttemplate.templates.feature_module.base.models.FeatureModuleData
import com.android.tools.idea.wizard.template.*
import com.android.tools.idea.wizard.template.impl.activities.common.MIN_API

val setupFeatureModule get() = template {

    revision = 1
    name = "Módulo de feature"
    description = "Adiciona um módulo de feature"
    minApi = MIN_API
    minBuildApi = MIN_API
    category = Category.Other
    formFactor = FormFactor.Mobile
    screens = listOf(
        WizardUiContext.FragmentGallery, WizardUiContext.MenuEntry,
        WizardUiContext.NewProject, WizardUiContext.NewModule
    )

    val packageName = defaultPackageNameParameter

    val featureName = stringParameter {
        name = "Nome da feature"
        default = "Feature X"
        help = "Nome da feature que será criada"
        constraints = listOf(Constraint.NONEMPTY)
    }

    val activityName = stringParameter {
        name = "Nome da Activity"
        default = "MainActivity"
        help = "Nome da Activity que será criada"
        constraints = listOf(Constraint.CLASS, Constraint.UNIQUE, Constraint.NONEMPTY)
    }

    val dependencyInjection = enumParameter<DependencyInjectionEnum> {
        name = "SDK para injeção de dependência"
        default = DependencyInjectionEnum.Koin
        help = "SDK que será usado para injeção de dependência"
    }

    widgets(
        PackageNameWidget(packageName),
        TextFieldWidget(featureName),
        TextFieldWidget(activityName),
        EnumWidget(dependencyInjection)
    )

    //TODO ícone do template
    // thumb { File("concreteplugin.png") }

    recipe = { data: TemplateData ->
        projectInstance?.let{ project ->

            FeatureModuleData(
                project,
                packageName.value,
                featureName.value.toLowerCase(),
                activityName.value,
                dependencyInjection.value
            ).let{
                FeatureModuleProject(it).setup()
            }

        }
    }

}