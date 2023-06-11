package com.example.footballapp

import com.example.footballapp.datasource.fixtures.LazySchedulers
import io.reactivex.schedulers.TestScheduler

@Suppress("MemberVisibilityCanBePrivate")
class LazyTestSchedulers(
    val testScheduler: TestScheduler = TestScheduler()
) : LazySchedulers {

    override fun io() = testScheduler

    override fun computation() = testScheduler

    override fun main() = testScheduler

    fun triggerActions() = testScheduler.triggerActions()
}
