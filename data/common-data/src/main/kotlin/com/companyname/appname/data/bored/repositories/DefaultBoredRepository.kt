package com.companyname.appname.data.bored.repositories

import com.companyname.appname.data.bored.datasources.BoredDataSource
import com.companyname.appname.domain.bored.entities.Activity
import com.companyname.appname.domain.bored.repositories.BoredRepository
import com.companyname.appname.domain.common.LCE
import io.reactivex.rxjava3.core.Single
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.rx3.rxSingle

class DefaultBoredRepository(
    private val dataSource: BoredDataSource,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : BoredRepository {

    override fun getRandomActivity(): Single<LCE<Activity>> = rxSingle(ioDispatcher) {
        dataSource.getRandomActivity()
    }
}
