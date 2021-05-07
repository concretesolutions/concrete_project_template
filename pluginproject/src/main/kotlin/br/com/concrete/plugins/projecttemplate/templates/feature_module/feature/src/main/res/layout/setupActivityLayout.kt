package br.com.concrete.plugins.projecttemplate.templates.feature_module.feature.src.main.res.layout

import br.com.concrete.plugins.projecttemplate.templates.feature_module.feature.src.main.res.MainResScope

fun MainResScope.setupActivityLayout() = createFile(
    name = "${data.activityName.toLowerCase()}.xml",
    rootDirectory = "${directory}/layout",
    content = """
<?xml version="1.0" encoding="utf-8"?>
<androidx.constraintlayout.widget.ConstraintLayout 
    xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    tools:context=".${data.activityName}">

</androidx.constraintlayout.widget.ConstraintLayout>
""")