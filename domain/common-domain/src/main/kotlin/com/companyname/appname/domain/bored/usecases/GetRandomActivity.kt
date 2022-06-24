package com.companyname.appname.domain.bored.usecases

import com.companyname.appname.domain.bored.entities.Activity
import com.companyname.appname.domain.bored.repositories.BoredRepository
import com.companyname.appname.domain.common.LCE
import io.reactivex.rxjava3.core.Observable

class GetRandomActivity(
    private val repository: BoredRepository
) {
    fun instance() = UseCase(repository)

    class UseCase(private val repository: BoredRepository) {
        fun execute(): Observable<LCE<Activity>> = repository.getRandomActivity().toObservable()
    }
}
