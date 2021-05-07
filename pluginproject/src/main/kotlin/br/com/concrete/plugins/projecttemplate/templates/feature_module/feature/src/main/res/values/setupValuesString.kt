package br.com.concrete.plugins.projecttemplate.templates.feature_module.feature.src.main.res.values

import br.com.concrete.plugins.projecttemplate.templates.feature_module.feature.src.main.res.MainResScope

fun MainResScope.setupValuesString() = createFile(
    name = "strings.xml",
    rootDirectory = "${directory}/values",
    content = """
<resources>
    <string name="feature_name">${data.featureName}</string>
</resources>
""")