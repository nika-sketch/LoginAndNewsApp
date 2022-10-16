package ge.nlatsabidze.newsapplication.di

import android.app.NotificationManager
import android.content.Context
import dagger.Module
import androidx.work.*
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton
import java.util.concurrent.TimeUnit
import dagger.hilt.components.SingletonComponent
import ge.nlatsabidze.newsapplication.presentation.ui.firebaseAuthentication.FirebaseEvent
import ge.nlatsabidze.newsapplication.presentation.ui.firebaseAuthentication.StartPeriodicWorkRequest
import ge.nlatsabidze.newsapplication.presentation.ui.firebaseAuthentication.WorkManagerWrapperContext
import ge.nlatsabidze.newsapplication.presentation.ui.notification.*
import javax.inject.Named

@Module
@InstallIn(SingletonComponent::class)
object WorkManagerModule {

    @Provides
    fun provideConstraints(): Constraints = Constraints.Builder()
        .setRequiredNetworkType(NetworkType.NOT_REQUIRED)
        .setRequiresCharging(false)
        .build()

    @Provides
    fun provideWorkRequest(constraints: Constraints): PeriodicWorkRequest =
        PeriodicWorkRequestBuilder<AbstractWork.WorkService>(16, TimeUnit.MINUTES)
            .setConstraints(constraints)
            .build()

    @Provides
    fun provideExistingPeriodicWorkPolicy(): ExistingPeriodicWorkPolicy =
        ExistingPeriodicWorkPolicy.KEEP

    @Provides
    fun provideWorkName(): String = "Work"

    @Provides
    fun provideWorkManagerWrapper(@ApplicationContext context: Context): WorkManagerWrapperContext =
        WorkManagerWrapperContext.Base(context)

    @Provides
    fun provideWorker(
        workManagerWrapperContext: WorkManagerWrapperContext,
        workRequest: PeriodicWorkRequest,
        workPolicy: ExistingPeriodicWorkPolicy,
        workName: String
    ): StartPeriodicWorkRequest =
        StartPeriodicWorkRequest.Base(workManagerWrapperContext, workRequest, workPolicy, workName)

    @Provides
    fun provideNotificationEvent(
        provideWorker: StartPeriodicWorkRequest
    ): FirebaseEvent = FirebaseEvent.Notification(provideWorker)

    @Provides
    @Singleton
    fun provideHandleNotification(
        randomWordsDescription: RandomWordsDescription,
        notificationBuilder: BuildNotification
    ): HandleNotification = HandleNotification.Base(randomWordsDescription, notificationBuilder)

    @Provides
    fun provideGenerateRandomNumber(): GenerateRandomNumber<Int> = GenerateRandomNumber.Base()

    @Provides
    fun provideShuffleList(): ShuffleList<GenerateContent> = ShuffleList.RandomShuffle()

    @Provides
    fun provideRandomDescription(
        GenerateRandomNumber: GenerateRandomNumber<Int>,
        shuffleList: ShuffleList<GenerateContent>
    ): RandomWordsDescription = RandomWordsDescription.Base(GenerateRandomNumber, shuffleList)

    @Provides
    fun provideNotificationCompatPriority(): Int = NotificationManager.IMPORTANCE_HIGH

    @Provides
    fun provideNotificationSound(): ProvideRingtoneManager =
        ProvideRingtoneManager.TypeNotification()

    @Provides
    @Named("notificationVibration")
    fun provideNotificationVibration(): ProvideNotificationImageAndVibration =
        ProvideNotificationImageAndVibration.NotificationVibration()

    @Provides
    @Named("notificationImage")
    fun provideNotificationImage(): ProvideNotificationImageAndVibration =
        ProvideNotificationImageAndVibration.NotificationImage()

    @Provides
    fun provideNotificationBuilderCompat(
        notificationCompatPriority: Int,
        provideRingToneManager: ProvideRingtoneManager,
        @Named("notificationVibration")
        notificationVibration: ProvideNotificationImageAndVibration,
        @Named("notificationImage")
        notificationImage: ProvideNotificationImageAndVibration
    ): BuildNotification =
        BuildNotification.Base(
            notificationCompatPriority,
            provideRingToneManager,
            notificationVibration,
            notificationImage
        )

}
