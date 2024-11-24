package com.febriandi.agrojaya.data.di

import com.febriandi.agrojaya.data.Repository.ArtikelRepository
import com.febriandi.agrojaya.data.Repository.ArtikelRepositoryImpl
import com.febriandi.agrojaya.data.Repository.PaketRepository
import com.febriandi.agrojaya.data.Repository.PaketRepositoryImpl
import com.febriandi.agrojaya.data.Repository.UserRepository
import com.febriandi.agrojaya.data.Repository.UserRepositoryImpl
import dagger.Binds
import dagger.Module
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
}