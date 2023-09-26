package com.example.myweather.util

interface Mapper<Input,Output> {

    fun mapFromDomain(domain: Input): Output

    fun mapToDomain(domain: Output): Input
}