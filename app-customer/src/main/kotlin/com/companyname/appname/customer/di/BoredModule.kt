package com.companyname.appname.customer.di

import com.companyname.appname.data.bored.repositories.DefaultBoredRepository
import com.companyname.appname.data.bored.datasources.BoredDataSource
import com.companyname.appname.data.bored.datasources.BoredDataSourceImpl
import com.companyname.appname.domain.bored.repositories.BoredRepository
import com.companyname.appname.domain.bored.usecases.GetRandomActivity
import com.companyname.appname.data.bored.api.BoredApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class BoredModule {

    @Provides
    @Singleton
    fun provideGetRandomActivityUseCase(repository: BoredRepository) =
        GetRandomActivity(repository)

    @Provides
    @Singleton
    fun provideBoredRepository(dataSource: BoredDataSource): BoredRepository =
        DefaultBoredRepository(dataSource)

    @Provides
    @Singleton
    fun provideBoredDataSource(api: BoredApi): BoredDataSource = BoredDataSourceImpl(api)
}
