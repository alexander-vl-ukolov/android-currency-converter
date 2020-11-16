package com.immortalalexsan.currencyconverter.domain.mappers

interface Mapper<I, O> {

    fun mapToInput(output: O): I

    fun mapToOutput(input: I): O
}
