package com.immortalalexsan.currencyconverter.data.repositories

import com.immortalalexsan.currencyconverter.domain.exceptions.Failure
import com.immortalalexsan.currencyconverter.domain.exceptions.FailureException
import io.reactivex.rxjava3.core.Single
import retrofit2.Response

abstract class BaseRepository {

    private fun <R> success(value: R): Single<R> = Single.just(value)

    protected fun <R> error(failure: Failure): Single<R> = Single.error(FailureException(failure))

    protected fun <T, R> request(single: Single<Response<T>>, transform: (T) -> R): Single<R> =
        try {
            single.flatMap { response ->
                val body = response.body()
                if (response.isSuccessful && body != null) success(transform(body))
                else error(Failure.ServerError)
            }
        } catch (e: Throwable) {
            error(Failure.IoError)
        }
}
