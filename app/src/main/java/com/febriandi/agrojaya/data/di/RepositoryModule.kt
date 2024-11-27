package com.febriandi.agrojaya.data.di

import com.febriandi.agrojaya.data.RepositoryImpl.AlamatRepositoryImpl
import com.febriandi.agrojaya.data.RepositoryImpl.ArtikelRepositoryImpl
import com.febriandi.agrojaya.data.RepositoryImpl.LocationRepositoryImpl
import com.febriandi.agrojaya.data.RepositoryImpl.PaketRepositoryImpl
import com.febriandi.agrojaya.data.RepositoryImpl.UserRepositoryImpl
import com.febriandi.agrojaya.data.Repository.*
import com.febriandi.agrojaya.data.RepositoryImpl.TransaksiRepositoryImpl
import com.febriandi.agrojaya.data.remote.LocationApiService
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindUserRepository(
        userRepositoryImpl: UserRepositoryImpl
    ): UserRepository

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

}