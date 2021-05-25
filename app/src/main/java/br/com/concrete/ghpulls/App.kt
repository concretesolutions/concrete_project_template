package br.com.concrete.ghpulls

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@App)

            val appModules = koinModules(
                getString(R.string.base_url_github_api),
                BuildConfig.DEBUG
            )
            modules(appModules)
        }
    }
}
