package com.mironov.newsapp.di

import android.content.Context
import com.mironov.newsapp.RetrofitInstrumentedTest
import dagger.*


@Component(modules = [AppModule::class,RetrofitModule::class, RoomModule::class])
@AppScope
interface TestAppComponent {

    fun inject(retrofitInstrumentedTest:RetrofitInstrumentedTest )

    @Component.Builder
    interface Builder {

        fun build(): TestAppComponent

        @BindsInstance
        fun context(context: Context): Builder
    }
}





