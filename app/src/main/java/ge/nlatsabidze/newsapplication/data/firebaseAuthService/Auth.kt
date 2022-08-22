package ge.nlatsabidze.newsapplication.data.firebaseAuthService

import javax.inject.Inject
import kotlinx.coroutines.tasks.await
import com.google.firebase.auth.FirebaseAuth

interface Auth {

    suspend fun register(email: String, password: String)

    suspend fun logIn(email: String, password: String)

    class Firebase @Inject constructor(private val firebaseAuth: FirebaseAuth) : Auth {

        override suspend fun register(email: String, password: String) {
            firebaseAuth.createUserWithEmailAndPassword(email, password).await()
        }

        override suspend fun logIn(email: String, password: String) {
            firebaseAuth.signInWithEmailAndPassword(email, password).await()
        }
    }
}
