package ge.nlatsabidze.newsapplication.presentation.ui.notification

import android.media.RingtoneManager
import android.net.Uri

interface ProvideRingtoneManager {

    fun ringToneNotification(): Uri

    abstract class Abstract(
        private val notificationType: Int
    ) : ProvideRingtoneManager {
        override fun ringToneNotification(): Uri = RingtoneManager.getDefaultUri(notificationType)
    }

    class TypeRingtone : Abstract(RingtoneManager.TYPE_RINGTONE)
    class TypeNotification : Abstract(RingtoneManager.TYPE_NOTIFICATION)
}