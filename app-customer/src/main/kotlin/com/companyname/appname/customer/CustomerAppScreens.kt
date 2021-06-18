package com.companyname.appname.customer

import android.content.Context
import android.content.Intent
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import com.companyname.appname.presentation.feature1.fragments.second.SecondFragment
import com.companyname.appname.presentation.feature2.Feature2Activity
import com.companyname.appname.presentation.common.Screens
import com.github.terrakok.cicerone.androidx.ActivityScreen
import com.github.terrakok.cicerone.androidx.Creator
import com.github.terrakok.cicerone.androidx.FragmentScreen

object CustomerAppScreens : Screens {

    override fun second(): FragmentScreen = FragmentScreen(
        fragmentCreator = object : Creator<FragmentFactory, Fragment> {
            override fun create(argument: FragmentFactory): Fragment = SecondFragment()
        })

    override fun feature2() =
        ActivityScreen(intentCreator = object : Creator<Context, Intent> {
            override fun create(argument: Context): Intent =
                Intent(argument, Feature2Activity::class.java)
        })
}
