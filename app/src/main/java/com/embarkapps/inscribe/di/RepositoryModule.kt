package com.embarkapps.inscribe.di

import com.embarkapps.inscribe.data.local.LocalStorageRepositoryImpl
import com.embarkapps.inscribe.domain.local.LocalStorageRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class RepositoryModule {
    @Binds
    abstract fun bindLocalRepository(impl: LocalStorageRepositoryImpl): LocalStorageRepository
}