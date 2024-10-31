package com.embarkapps.inscribe.di

import android.content.Context
import androidx.room.Room
import com.embarkapps.inscribe.core.domain.Constants
import com.embarkapps.inscribe.core.presentation.util.navigation.DefaultNavigator
import com.embarkapps.inscribe.core.presentation.util.navigation.Destination
import com.embarkapps.inscribe.core.presentation.util.navigation.Navigator
import com.embarkapps.inscribe.notes.data.local.db.NoteDao
import com.embarkapps.inscribe.notes.data.local.db.NoteDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class AppModule {
    @Provides
    @Singleton
    fun provideNoteDao(database: NoteDatabase): NoteDao = database.noteDao()


    @Provides
    @Singleton
    fun provideNoteDatabase(@ApplicationContext context: Context): NoteDatabase =
        Room.databaseBuilder(
            context,
            NoteDatabase::class.java,
            Constants.DATABASE_NAME
        ).fallbackToDestructiveMigration()
            .build()

    @Provides
    @Singleton
    fun provideNavigator(): Navigator = DefaultNavigator(
        startDestination = Destination.NotesGraph
    )
}