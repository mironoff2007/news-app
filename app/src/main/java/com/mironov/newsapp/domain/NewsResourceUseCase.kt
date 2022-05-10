package com.mironov.newsapp.domain

import com.mironov.newsapp.domain.NewsResources.*
import com.mironov.newsapp.repository.retrofit.JsonResponse
import io.reactivex.Single

class NewsResourceUseCase: UseCase<(domain: String?, source: String?) -> Single<JsonResponse?>, NewsResources, JsonResponse?> {
    override fun execute(
        request: ((domain: String?, source: String?) -> Single<JsonResponse?>),
        param: NewsResources?
    ): Single<JsonResponse?> {
        return when (param) {
            LENTA -> request.invoke(LENTA.domain, null)
            VEDOMOSTI -> request.invoke(VEDOMOSTI.domain, null)
            RBC -> request.invoke(null, RBC.source)
            else -> request.invoke(null, null)
        }
    }


}