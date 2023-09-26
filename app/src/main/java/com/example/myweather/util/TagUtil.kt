package com.example.myweather.util

object TagUtil{
    val Any.TAG: String get() = this.javaClass.name
}