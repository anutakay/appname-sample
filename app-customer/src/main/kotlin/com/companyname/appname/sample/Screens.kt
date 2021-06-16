package com.companyname.appname.sample

import android.content.Context
import android.content.Intent
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import com.companyname.appname.feature1.fragments.second.SecondFragment
import com.companyname.appname.feature_2_presentation.Feature2Activity
import com.companyname.appname.presentation.common.IScreens
import com.github.terrakok.cicerone.androidx.ActivityScreen
import com.github.terrakok.cicerone.androidx.Creator
import com.github.terrakok.cicerone.androidx.FragmentScreen

object Screens : IScreens {

    override fun second(): FragmentScreen = FragmentScreen(
        fragmentCreator = object : Creator<FragmentFactory, Fragment> {
            override fun create(argument: FragmentFactory): Fragment = SecondFragment()
        })

    override fun feature2() =
        ActivityScreen(intentCreator = object : Creator<Context, Intent> {
            override fun create(context: Context): Intent =
                Intent(context, Feature2Activity::class.java)
        })
}
