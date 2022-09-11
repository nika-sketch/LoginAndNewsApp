package ge.nlatsabidze.newsapplication.presentation.ui.notification

import android.app.NotificationChannel
import android.app.NotificationManager

interface ChannelNotification {

    fun provideNotificationChannel(): NotificationChannel

    class Base(
        private val channelImportance: Int = NotificationManager.IMPORTANCE_HIGH
    ) : ChannelNotification {

        companion object {
            private const val CHANNEL_ID = "channel_id"
        }

        override fun provideNotificationChannel(): NotificationChannel = NotificationChannel(
            CHANNEL_ID,
            "Channel Name",
            channelImportance
        ).apply {
            description = "Channel Description"
        }
    }
}