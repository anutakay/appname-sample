package com.companyname.appname.domain.bored.usecases

import com.companyname.appname.domain.bored.repositories.BoredRepository

class GetRandomActivityUseCase(
    private val repository: BoredRepository
) {
    suspend operator fun invoke() = repository.getRandomActivity()
}
