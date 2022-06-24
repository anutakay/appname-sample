package com.companyname.appname.domain.bored.repositories

import com.companyname.appname.domain.bored.entities.Activity
import com.companyname.appname.domain.common.LCE
import io.reactivex.rxjava3.core.Single

interface BoredRepository {
    fun getRandomActivity(): Single<LCE<Activity>>
}
