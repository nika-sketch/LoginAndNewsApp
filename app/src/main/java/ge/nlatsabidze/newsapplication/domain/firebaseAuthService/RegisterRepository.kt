package ge.nlatsabidze.newsapplication.domain.firebaseAuthService

import ge.nlatsabidze.newsapplication.presentation.ui.firebaseAuthentication.UserAuthResult

interface RegisterRepository {

    suspend fun register(name: String, email: String, password: String): UserAuthResult
}