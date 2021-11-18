package com.tatatutest.tatatucontacts.app

import android.content.Context
import com.tatatutest.tatatucontacts.app.utils.StringResourceProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApplicationModule {
    @Provides
    @Singleton
    fun provideStringResource(@ApplicationContext context: Context): StringResourceProvider {
        return StringResourceProvider(context)
    }
}
