package ge.nlatsabidze.newsapplication.di

import com.google.firebase.auth.FirebaseAuth
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ge.nlatsabidze.newsapplication.core.Communication
import ge.nlatsabidze.newsapplication.core.ProvideResources
import ge.nlatsabidze.newsapplication.core.Visibility
import ge.nlatsabidze.newsapplication.data.firebaseAuthService.*
import ge.nlatsabidze.newsapplication.data.interactor.RegisterInteractorImpl
import ge.nlatsabidze.newsapplication.data.interactor.SignInInteractorImpl
import ge.nlatsabidze.newsapplication.data.repository.RegisterRepositoryImpl
import ge.nlatsabidze.newsapplication.data.repository.SignInRepositoryImpl
import ge.nlatsabidze.newsapplication.domain.firebaseAuthService.RegisterRepository
import ge.nlatsabidze.newsapplication.domain.interactor.SignInInteractor
import ge.nlatsabidze.newsapplication.domain.firebaseAuthService.SignInRepository
import ge.nlatsabidze.newsapplication.domain.interactor.RegisterInteractor
import ge.nlatsabidze.newsapplication.presentation.ui.core.Text
import ge.nlatsabidze.newsapplication.presentation.ui.firebaseAuthentication.FirebaseEvent
import javax.inject.Named

@Module
@InstallIn(SingletonComponent::class)
object FirebaseModule {

    @Provides
    fun provideFirebaseAuth(): FirebaseAuth = FirebaseAuth.getInstance()

    @Provides
    fun provideSingInRepository(
        @Named("logIn")registerAuthentication: FirebaseAuthentication
    ): SignInRepository =
        SignInRepositoryImpl(registerAuthentication)

    @Provides
    fun provideRegisterRepository(
        @Named("register") registerAuthentication: FirebaseAuthentication
    ): RegisterRepository = RegisterRepositoryImpl(registerAuthentication)

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
    fun provideSignInChannel(): Communication<FirebaseEvent> = Communication.FirebaseAuthEvent()

    @Provides
    fun provideLoadingCommunication(): Communication<Visibility> =
        Communication.BaseLoading(Visibility.Gone())

    @Provides
    fun provideText(): Text = Text.Base()

    @Provides
    fun provideAuth(firebaseAuth: FirebaseAuth): Auth = Auth.Firebase(firebaseAuth)

    @Provides
    @Named("register")
    fun provideRegisterAuthentication(auth: Auth): FirebaseAuthentication = FirebaseAuthentication.Register(auth)

    @Provides
    @Named("logIn")
    fun provideSignInAuthentication(auth: Auth): FirebaseAuthentication = FirebaseAuthentication.Login(auth)
}
