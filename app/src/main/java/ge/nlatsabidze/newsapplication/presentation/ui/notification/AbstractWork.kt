package ge.nlatsabidze.newsapplication.presentation.ui.notification

import androidx.work.Worker
import android.content.Context
import dagger.assisted.Assisted
import androidx.hilt.work.HiltWorker
import dagger.assisted.AssistedInject
import androidx.work.WorkerParameters

abstract class AbstractWork(
    context: Context,
    workerParameters: WorkerParameters,
    private val handleNotification: HandleNotification
) : Worker(context, workerParameters) {

    override fun doWork(): Result {
        handleNotification.createNotification(applicationContext)
        return Result.success()
    }

    @HiltWorker
    class WorkService @AssistedInject constructor(
        @Assisted context: Context,
        @Assisted workerParameters: WorkerParameters,
        handleNotification: HandleNotification
    ) : AbstractWork(context, workerParameters, handleNotification)

}