package ge.nlatsabidze.newsapplication.di


import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import android.content.Context
import ge.nlatsabidze.newsapplication.core.*
import dagger.hilt.components.SingletonComponent
import dagger.hilt.android.qualifiers.ApplicationContext

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun provideResponseHandler(
        handleResult: HandleResult,
        errorProvide: Error,
        handleException: HandleException
    ): HandleResponse =
        HandleResponse.Base(handleResult, errorProvide, handleException)

    @Provides
    fun provideError(resources: ProvideResources): Error = Error.NoConnection(resources)

    @Provides
    fun provideException(): HandleException = HandleException.Base()

    @Provides
    fun provideObserveConnectivity(@ApplicationContext context: Context): ObserveConnectivity =
        ObserveConnectivity.Base(context)

    @Provides
    fun provideObserveConnectivityChannel(): Communication<Status> =
        Communication.ObserveConnection()
}
