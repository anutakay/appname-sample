package com.companyname.appname.data.bored.datasources

import com.companyname.appname.domain.bored.entities.Activity
import com.companyname.appname.domain.common.Result

interface BoredDataSource {
    suspend fun getRandomActivity(): Result<Activity>
}
