package com.febriandi.agrojaya.data.di

import android.content.Context
import androidx.hilt.work.HiltWorkerFactory
import androidx.work.WorkerFactory
import com.febriandi.agrojaya.data.RepositoryImpl.AlamatRepositoryImpl
import com.febriandi.agrojaya.data.RepositoryImpl.ArtikelRepositoryImpl
import com.febriandi.agrojaya.data.RepositoryImpl.LocationRepositoryImpl
import com.febriandi.agrojaya.data.RepositoryImpl.PaketRepositoryImpl
import com.febriandi.agrojaya.data.RepositoryImpl.UserRepositoryImpl
import com.febriandi.agrojaya.data.Repository.*
import com.febriandi.agrojaya.data.RepositoryImpl.NotificationRepositoryImpl
import com.febriandi.agrojaya.data.RepositoryImpl.ProfileRepositoryImpl
import com.febriandi.agrojaya.data.RepositoryImpl.TransaksiRepositoryImpl
import com.febriandi.agrojaya.data.remote.ApiService
import com.febriandi.agrojaya.data.remote.LocationApiService
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindArtikelRepository(
        artikelRepositoryImpl: ArtikelRepositoryImpl
    ): ArtikelRepository

    @Binds
    @Singleton
    abstract fun bindPaketRepository(
        paketRepositoryImpl: PaketRepositoryImpl
    ): PaketRepository

    @Binds
    @Singleton
    abstract fun bindAlamatRepository(
        alamatRepositoryImpl: AlamatRepositoryImpl
    ): AlamatRepository

    @Binds
    @Singleton
    abstract fun bindTransaksiRepository(
        transaksiRepositoryImpl: TransaksiRepositoryImpl
    ): TransaksiRepository
}

@Module
@InstallIn(SingletonComponent::class)
object RepositoryProviderModule {

    @Provides
    @Singleton
    fun provideLocationRepository(
        locationApiService: LocationApiService
    ): LocationRepository {
        return LocationRepositoryImpl(locationApiService)
    }

    @Provides
    @Singleton
    fun provideProfileRepository(
        firebaseAuth: FirebaseAuth,
        apiService: ApiService,
        @ApplicationContext context: Context
    ): ProfileRepository {
        return ProfileRepositoryImpl(firebaseAuth, apiService, context)
    }

    @Provides
    @Singleton
    fun provideUserRepository(apiService: ApiService): UserRepository {
        return UserRepositoryImpl(apiService)
    }

    @Provides
    @Singleton
    fun provideNotificationRepository(
        database: FirebaseDatabase,
        firebaseAuth: FirebaseAuth
    ): NotificationRepository {
        return NotificationRepositoryImpl(database, firebaseAuth)
    }
}