package ge.nlatsabidze.newsapplication.data.firebaseAuthService

import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

interface Auth {

    suspend fun register(email: String, password: String): AuthResult

    suspend fun signIn(email: String, password: String): AuthResult

    class Firebase @Inject constructor(private val firebaseAuth: FirebaseAuth) : Auth {

        override suspend fun register(email: String, password: String): AuthResult =
            firebaseAuth.createUserWithEmailAndPassword(email, password).await()

        override suspend fun signIn(email: String, password: String): AuthResult =
            firebaseAuth.signInWithEmailAndPassword(email, password).await()
    }
}