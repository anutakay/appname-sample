package com.companyname.appname.domain.bored.repositories

import com.companyname.appname.domain.bored.entities.Activity
import com.companyname.appname.domain.common.Result

interface BoredRepository {
    suspend fun getRandomActivity(): Result<Activity>
}
