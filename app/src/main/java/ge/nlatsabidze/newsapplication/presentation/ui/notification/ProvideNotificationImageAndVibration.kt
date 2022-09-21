package ge.nlatsabidze.newsapplication.presentation.ui.notification

import android.app.Notification
import ge.nlatsabidze.newsapplication.R

interface ProvideNotificationImageAndVibration {

    fun provideVibrationAndIcon(): Int

    abstract class Abstract(
        private val notificationVibration: Int
    ) : ProvideNotificationImageAndVibration {
        override fun provideVibrationAndIcon(): Int = notificationVibration
    }

    class NotificationVibration : Abstract(Notification.DEFAULT_VIBRATE)
    class NotificationImage : Abstract(R.drawable.ic_github)
}