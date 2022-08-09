package ge.nlatsabidze.newsapplication.di

import com.google.firebase.auth.FirebaseAuth
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ge.nlatsabidze.newsapplication.core.Communication
import ge.nlatsabidze.newsapplication.core.ProvideResources
import ge.nlatsabidze.newsapplication.core.Visibility
import ge.nlatsabidze.newsapplication.data.firebaseAuthService.RegisterInteractorImpl
import ge.nlatsabidze.newsapplication.data.firebaseAuthService.RegisterRepositoryImpl
import ge.nlatsabidze.newsapplication.data.firebaseAuthService.SignInInteractorImpl
import ge.nlatsabidze.newsapplication.data.firebaseAuthService.SignInRepositoryImpl
import ge.nlatsabidze.newsapplication.domain.firebaseAuthService.RegisterRepository
import ge.nlatsabidze.newsapplication.domain.interactor.SignInInteractor
import ge.nlatsabidze.newsapplication.domain.firebaseAuthService.SignInRepository
import ge.nlatsabidze.newsapplication.domain.interactor.RegisterInteractor
import ge.nlatsabidze.newsapplication.presentation.ui.core.Text
import ge.nlatsabidze.newsapplication.presentation.ui.firebaseAuthentication.SignInEvent

@Module
@InstallIn(SingletonComponent::class)
object FirebaseModule {

    @Provides
    fun provideFirebaseAuth(): FirebaseAuth = FirebaseAuth.getInstance()

    @Provides
    fun provideSingInRepository(
        firebaseAuth: FirebaseAuth
    ): SignInRepository =
        SignInRepositoryImpl(firebaseAuth)

    @Provides
    fun provideRegisterRepository(
        firebaseAuth: FirebaseAuth
    ): RegisterRepository = RegisterRepositoryImpl(firebaseAuth)

    @Provides
    fun provideSignInInteractor(
        signInRepository: SignInRepository,
        provideResources: ProvideResources
    ): SignInInteractor = SignInInteractorImpl(signInRepository, provideResources)

    @Provides
    fun provideRegisterInteractor(
        registerRepository: RegisterRepository,
        provideResources: ProvideResources
    ): RegisterInteractor = RegisterInteractorImpl(registerRepository, provideResources)

    @Provides
    fun provideSignInChannel(): Communication<SignInEvent> = Communication.BaseSignInEvent()

    @Provides
    fun provideLoadingCommunication(): Communication<Visibility> =
        Communication.BaseLoading(Visibility.Gone())

    @Provides
    fun provideText(): Text = Text.Base()
}
