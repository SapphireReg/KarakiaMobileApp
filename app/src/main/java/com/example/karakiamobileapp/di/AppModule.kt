package com.example.karakiamobileapp.di

import android.app.Application
import androidx.room.Room
import com.example.karakiamobileapp.data.KarakiaDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import javax.inject.Qualifier
import javax.inject.Singleton


@Module
@InstallIn(ApplicationComponent::class)
object AppModule {

    @Provides //tells dagger this is instruction function
    @Singleton //only creates 1 instance of database
    fun  provideDatabase(
        app: Application,
        callback: KarakiaDatabase.Callback
    ) = Room.databaseBuilder(app, KarakiaDatabase::class.java, "karakia_database")
        .fallbackToDestructiveMigration() //update DBschema but don't have proper migration strategy = drops the table and creates a new one
        .addCallback(callback)
        .build() //creates one instance to task database class

    @Provides
    fun provideKarakiaDao(db: KarakiaDatabase) = db.karakiaDao()

    @ApplicationScope
    @Provides
    @Singleton
    fun provideApplicationScope() = CoroutineScope(SupervisorJob())
}
    @Retention(AnnotationRetention.RUNTIME) //interoperability
    @Qualifier
    annotation class ApplicationScope

