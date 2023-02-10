package com.blesscompany.hellishweek.injector

import com.blesscompany.hellishweek.common.expectation.Application
import org.koin.core.KoinApplication
import org.koin.core.context.startKoin
import org.koin.core.module.Module
import org.koin.dsl.module

object Injector {

    lateinit var koinApplication: KoinApplication

    inline fun <reified T : Any> get(): T {
        return koinApplication.koin.get()
    }

    fun initKoin(application: Application, modules: List<Module>) {
        koinApplication = startKoin {
            modules(
                modules.toMutableSet().apply {
                    add(module {
                        single { application }
                    })
                    add(domainModule)
                }.toList()
            )
        }
    }
}

val domainModule = module {
    // TODO add some implementations
}