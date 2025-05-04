package com.embarkapps.inscribe.core.di

import com.embarkapps.inscribe.feature.notes.data.local.LocalStorageRepositoryImpl
import com.embarkapps.inscribe.feature.notes.domain.local.LocalStorageRepository
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