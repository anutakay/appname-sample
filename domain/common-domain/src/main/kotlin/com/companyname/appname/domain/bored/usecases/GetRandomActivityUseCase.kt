package com.companyname.appname.domain.bored.usecases

import com.companyname.appname.domain.bored.entities.Activity
import com.companyname.appname.domain.bored.repositories.BoredRepository
import com.companyname.appname.domain.common.Result

class GetRandomActivityUseCase(
    private val repository: BoredRepository
) {
    suspend operator fun invoke(): Result<Activity> = repository.getRandomActivity()
}
