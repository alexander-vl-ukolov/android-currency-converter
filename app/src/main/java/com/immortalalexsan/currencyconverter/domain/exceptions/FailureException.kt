package com.immortalalexsan.currencyconverter.domain.exceptions

class FailureException(val failure: Failure, message: String? = null) : Throwable(message)
