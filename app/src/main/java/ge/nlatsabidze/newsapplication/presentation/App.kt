package ge.nlatsabidze.newsapplication.presentation

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import ge.nlatsabidze.newsapplication.di.*
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

@HiltAndroidApp
class App: Application()
