package com.companyname.appname.presentation.feature2

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.companyname.appname.feature_2_presentation.R
import com.github.terrakok.cicerone.Navigator
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.androidx.AppNavigator
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class Feature2Activity : AppCompatActivity() {

    @Inject
    lateinit var navigatorHolder: NavigatorHolder

    private lateinit var navigator: Navigator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_feature2)
        navigator = AppNavigator(this, R.id.feature2_fragment_container)
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
