package com.immortalalexsan.currencyconverter.domain.exceptions

sealed class Failure {

    object ConnectionError : Failure()
    object ServerError : Failure()
    object IoError : Failure()

    abstract class FeatureFailure : Failure()
}
