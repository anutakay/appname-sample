package com.companyname.appname.data.bored.datasources

import com.companyname.appname.domain.bored.entities.Activity
import com.companyname.appname.domain.common.LCE

interface BoredDataSource {
    suspend fun getRandomActivity(): LCE<Activity>
}
