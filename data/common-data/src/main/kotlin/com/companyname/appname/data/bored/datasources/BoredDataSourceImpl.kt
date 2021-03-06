package com.companyname.appname.data.bored.datasources

import com.companyname.appname.domain.bored.entities.Activity
import com.companyname.appname.domain.common.LCE
import com.companyname.appname.data.bored.api.BoredApi
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.awaitResponse

class BoredDataSourceImpl(
    private val api: BoredApi,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : BoredDataSource {

    override suspend fun getRandomActivity(): LCE<Activity> = withContext(ioDispatcher) {
        val response = api.getRandomActivity().awaitResponse()
        return@withContext if (response.isSuccessful) {
            val body = response.body()!!
            LCE.Content(
                Activity(
                    activity = body.activity,
                    type = body.type,
                    participants = body.participants,
                    price = body.price,
                    link = body.link,
                    key = body.key,
                    accessibility = body.accessibility
                )
            )
        } else {
            LCE.Error(IllegalStateException("Response error"))
        }
    }
}
