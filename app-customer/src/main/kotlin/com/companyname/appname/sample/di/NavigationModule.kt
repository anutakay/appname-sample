package com.companyname.appname.sample.di

import com.companyname.appname.presentation.common.IScreens
import com.companyname.appname.sample.Screens
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
    fun provideScreens(): IScreens = object : IScreens {

        override fun second() = Screens.second()

        override fun feature2() = Screens.feature2()
    }
}