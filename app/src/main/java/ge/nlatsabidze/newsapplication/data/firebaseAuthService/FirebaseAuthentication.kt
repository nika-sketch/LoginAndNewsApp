package ge.nlatsabidze.newsapplication.data.firebaseAuthService

import javax.inject.Inject
import ge.nlatsabidze.newsapplication.presentation.ui.firebaseAuthentication.UserAuthResult

interface FirebaseAuthentication {

    suspend fun authenticateUser(email: String, password: String): UserAuthResult

    abstract class Abstract : FirebaseAuthentication {

        abstract fun userAuthResult(): UserAuthResult
        abstract suspend fun authResult(email: String, password: String)

        override suspend fun authenticateUser(email: String, password: String): UserAuthResult =
            try {
                authResult(email, password)
                userAuthResult()
            } catch (e: Exception) {
                UserAuthResult.ExceptionAuth(e.message!!)
            }
    }

    abstract class AuthAbstract(private val userAuthResult: UserAuthResult) : Abstract() {
        override fun userAuthResult(): UserAuthResult = userAuthResult
    }

    class Register @Inject constructor(
        private val auth: Auth
    ) : AuthAbstract(UserAuthResult.SuccessRegisterAuth()) {
        override suspend fun authResult(email: String, password: String) = auth.register(email, password)
    }

    class Login @Inject constructor(
        private val auth: Auth
    ) : AuthAbstract(UserAuthResult.SuccessLoginAuth()) {
        override suspend fun authResult(email: String, password: String) = auth.logIn(email, password)
    }
}