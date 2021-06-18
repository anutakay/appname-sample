package com.companyname.appname.data.bored.repositories

import com.companyname.appname.data.bored.datasources.BoredDataSource
import com.companyname.appname.domain.bored.entities.Activity
import com.companyname.appname.domain.bored.repositories.BoredRepository
import com.companyname.appname.domain.common.Result
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class DefaultBoredRepository(
    private val dataSource: BoredDataSource,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : BoredRepository {

    override suspend fun getRandomActivity(): Result<Activity> = withContext(ioDispatcher) {
        return@withContext dataSource.getRandomActivity()
    }
}
