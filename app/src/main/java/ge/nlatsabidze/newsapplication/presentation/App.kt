package ge.nlatsabidze.newsapplication.presentation

import android.app.Application
import ge.nlatsabidze.newsapplication.di.*
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            modules(listOf(viewModelModule, provideRetrofit, provideResponse, provideOther))
        }
    }

}
