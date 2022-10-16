package ge.nlatsabidze.newsapplication.presentation.ui.firebaseAuthentication

import android.content.Context
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.PeriodicWorkRequest
import androidx.work.WorkManager
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

interface StartPeriodicWorkRequest {

    fun startWork()

    class Base @Inject constructor(
        private val workManagerWrapperContext: WorkManagerWrapperContext,
        private val workRequest: PeriodicWorkRequest,
        private val existingPeriodicWorkPolicy: ExistingPeriodicWorkPolicy,
        private val workName: String
    ) : StartPeriodicWorkRequest {
        override fun startWork() {
            workManagerWrapperContext
                .applyWorkManagerInstance()
                .enqueueUniquePeriodicWork(
                    workName,
                    existingPeriodicWorkPolicy,
                    workRequest
                )
        }
    }
}

interface WorkManagerWrapperContext {

    fun applyWorkManagerInstance(): WorkManager

    class Base @Inject constructor(
        @ApplicationContext private val context: Context
    ) : WorkManagerWrapperContext {
        override fun applyWorkManagerInstance(): WorkManager = WorkManager.getInstance(context)
    }
}