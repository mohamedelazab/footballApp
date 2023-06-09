package com.example.footballapp.datasource.fixtures

import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class LazySchedulersImp : LazySchedulers {
    override fun io() = Schedulers.io()
    override fun computation() = Schedulers.computation()
    override fun main(): Scheduler = AndroidSchedulers.mainThread()
}
