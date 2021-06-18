package com.companyname.appname.customer.di

import com.companyname.appname.presentation.common.Screens
import com.companyname.appname.customer.CustomerAppScreens
import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.Router
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class NavigationModule {
    private val cicerone: Cicerone<Router> = Cicerone.create()

    @Provides
    @Singleton
    fun provideRouter(): Router = cicerone.router

    @Provides
    @Singleton
    fun provideNavigatorHolder(): NavigatorHolder = cicerone.getNavigatorHolder()

    @Provides
    @Singleton
    fun provideScreens(): Screens = object : Screens {

        override fun second() = CustomerAppScreens.second()

        override fun feature2() = CustomerAppScreens.feature2()
    }
}
