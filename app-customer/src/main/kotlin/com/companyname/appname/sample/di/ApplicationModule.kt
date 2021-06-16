package com.companyname.appname.sample.di

import com.companyname.appname.feature1.di.AppHash
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class ApplicationModule {

    @Provides
    @AppHash
    fun provideHash(): String = hashCode().toString()
}
