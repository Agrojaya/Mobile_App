package com.febriandi.agrojaya.data.di

import com.febriandi.agrojaya.data.Repository.UserGoogleRepository
import com.febriandi.agrojaya.data.Repository.UserGoogleRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryGoogleModule {

    @Binds
    @Singleton
    abstract fun bindUserRepository(
        userGoogleRepositoryImpl: UserGoogleRepositoryImpl
    ): UserGoogleRepository
}
