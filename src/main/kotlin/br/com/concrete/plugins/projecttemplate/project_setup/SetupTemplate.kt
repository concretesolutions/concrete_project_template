package br.com.concrete.plugins.projecttemplate.project_setup

import com.android.tools.idea.wizard.template.*
import com.android.tools.idea.wizard.template.impl.activities.common.MIN_API
import br.com.concrete.plugins.projecttemplate.base.enums.DependencyInjectionEnum
import br.com.concrete.plugins.projecttemplate.base.models.SetupData
import java.io.File

val setupTemplate
    get() = template {
        revision = 1
        name = "Concrete Project Template"
        description = "Creates a new project with Concrete Template"
        minApi = MIN_API
        minBuildApi = MIN_API
        category = Category.Other
        formFactor = FormFactor.Mobile
        screens = listOf(WizardUiContext.NewProject)

        val packageName = defaultPackageNameParameter

        lateinit var layoutName: StringParameter
        val activityClass = stringParameter {
            name = "Activity Name"
            default = "MainActivity"
            help = "The name of the activity class to create"
            constraints = listOf(Constraint.CLASS, Constraint.UNIQUE, Constraint.NONEMPTY)
            suggest = { layoutToActivity(layoutName.value) }
        }

        layoutName = stringParameter {
            name = "Layout Name"
            default = "activity_main"
            help = "The name of the layout to create for the activity"
            constraints = listOf(Constraint.LAYOUT, Constraint.UNIQUE, Constraint.NONEMPTY)
            suggest = { activityToLayout(activityClass.value) }
        }

        val activityTitle = stringParameter {
            name = "Title"
            default = "MainActivity"
            help = "The name of the activity. For launcher activities, the application title"
            visible = { false }
            constraints = listOf(Constraint.NONEMPTY)
            suggest = { activityClass.value }
        }

        val addSharedTests = booleanParameter {
            name = "Adicionar pasta para shared tests"
            default = true
            help = "Cria as pastas para shared tests"
        }

        val dependencyInjection = enumParameter<DependencyInjectionEnum> {
            name = "SDK para injeção de dependência"
            default = DependencyInjectionEnum.KOIN
            help = "SDK que será usado para injeção de dependência"
        }

        widgets(
            TextFieldWidget(activityClass),
            TextFieldWidget(layoutName),
            PackageNameWidget(packageName),
            LanguageWidget(),
            CheckBoxWidget(addSharedTests),
            EnumWidget(dependencyInjection)
        )

        thumb { File("concreteplugin.png") }

        recipe = { data: TemplateData ->
            SetupData(
                packageName.value,
                activityTitle.value,
                activityClass.value,
                layoutName.value,
                addSharedTests.value,
                dependencyInjection.value
            ).let {
                setupRecipe(data as ModuleTemplateData, it)
            }
        }
    }

val defaultPackageNameParameter get() = stringParameter {
    name = "Package name"
    visible = { !isNewModule }
    default = "com.mycompany.myapp"
    constraints = listOf(Constraint.PACKAGE)
    suggest = { packageName }
}