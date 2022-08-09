package ge.nlatsabidze.newsapplication.domain.firebaseAuthService

import ge.nlatsabidze.newsapplication.presentation.ui.firebaseAuthentication.UserAuthResult

interface SignInRepository {

    suspend fun signIn(email: String, password: String): UserAuthResult
}

