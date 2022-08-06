package ge.nlatsabidze.newsapplication.domain.signIn

import ge.nlatsabidze.newsapplication.presentation.ui.signIn.UserAuthResult

interface SignInRepository {

    suspend fun signIn(email: String, password: String): UserAuthResult
}
