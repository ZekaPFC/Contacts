package com.tatatutest.tatatucontacts.data.database

import android.content.Context
import androidx.room.Room
import com.tatatutest.tatatucontacts.app.utils.Constants
import com.tatatutest.tatatucontacts.data.contacts.ContactsDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Provides
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(context, AppDatabase::class.java, Constants.DATABASE_NAME)
            .build()
    }

    @Provides
    fun provideContactsDao(appDatabase: AppDatabase): ContactsDao {
        return appDatabase.contactsDao()
    }
}
