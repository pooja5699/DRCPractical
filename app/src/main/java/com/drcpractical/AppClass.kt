package com.drcpractical

import android.app.Application
import com.drcpractical.network.APIService
import com.drcpractical.network.NetworkConnectionInterceptor
import com.drcpractical.network.repository.DashboardRepository
import com.drcpractical.network.viewmodel.DashboardViewModelFactory
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton

class AppClass : Application(), KodeinAware {

    override val kodein: Kodein = Kodein.lazy {
        import(androidXModule(this@AppClass))

        bind() from singleton { NetworkConnectionInterceptor(instance()) }
        bind() from singleton { APIService(instance()) }
        bind() from singleton { DashboardRepository(instance()) }
        bind() from provider { DashboardViewModelFactory(instance()) }
    }

    companion object {
        private var instance: AppClass? = null

        @JvmStatic
        fun getInstance(): AppClass {
            return instance as AppClass
        }
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }

}