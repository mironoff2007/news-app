package com.mironov.newsapp.domain

import io.reactivex.Single

interface UseCase<T, P, R> {
    fun execute(request: T, param : P?): Single<R>
}