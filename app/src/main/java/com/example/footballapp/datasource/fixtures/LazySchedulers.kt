package com.example.footballapp.datasource.fixtures

import io.reactivex.Scheduler

interface LazySchedulers {
    fun io(): Scheduler
    fun computation(): Scheduler
    fun main(): Scheduler
}
