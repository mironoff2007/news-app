package com.mironov.newsapp.di

import android.content.Context
import com.mironov.newsapp.DetailsFragmentTest
import com.mironov.newsapp.RetrofitInstrumentedTest
import dagger.*


@Component(
    modules = [RepositoryModule::class,
        DataSharedModule::class,
        RetrofitModule::class,
        ViewModelModule::class,
        GlideModule::class,
        RoomModule::class,
        ResourcesModule::class]
)
@AppScope
interface TestAppComponent {

    fun inject(retrofitInstrumentedTest: RetrofitInstrumentedTest)
    fun inject(detailsFragmentTest: DetailsFragmentTest)

    @Component.Builder
    interface Builder {

        fun build(): TestAppComponent

        @BindsInstance
        fun context(context: Context): Builder
    }
}





