package com.companyname.appname.feature1

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.companyname.appname.feature1.di.AppHash
import com.github.terrakok.cicerone.Navigator
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.androidx.AppNavigator
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class Feature1Activity : AppCompatActivity() {

    @Inject
    lateinit var navigatorHolder: NavigatorHolder

    private lateinit var navigator: Navigator

    @AppHash
    @Inject
    lateinit var hash: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        navigator = AppNavigator(this, R.id.feature1_fragment_container)
        println("hash:" + hash)
    }

    override fun onResume() {
        super.onResume()
        navigatorHolder.setNavigator(navigator)
    }

    override fun onPause() {
        navigatorHolder.removeNavigator()
        super.onPause()
    }
}
