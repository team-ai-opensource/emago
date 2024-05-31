package ai.opensource.emago.di

import ai.opensource.emago.data.*
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideFirebaseAuth(): FirebaseAuth = FirebaseAuth.getInstance()

    @Provides
    @Singleton
    fun provideFirebaseFirestore(): FirebaseFirestore = FirebaseFirestore.getInstance()

    @Provides
    @Singleton
    fun provideFirebaseStorage(): FirebaseStorage = FirebaseStorage.getInstance()

    @Provides
    @Singleton
    fun provideAuthRepository(auth: FirebaseAuth, db: FirebaseFirestore): AuthRepository {
        return AuthRepository(auth, db)
    }

    @Provides
    @Singleton
    fun provideDBRepository(auth: FirebaseAuth, db: FirebaseFirestore, storage: FirebaseStorage): DBRepository {
        return DBRepository(auth, db, storage)
    }

    @Provides
    @Singleton
    fun provideChatRepository(auth: FirebaseAuth, db: FirebaseFirestore): ChatRepository {
        return ChatRepository(auth, db)
    }
}
