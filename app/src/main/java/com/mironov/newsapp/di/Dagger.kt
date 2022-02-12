package com.mironov.newsapp.di

import Repository
import android.content.Context
import androidx.lifecycle.ViewModel
import com.mironov.newsapp.repository.DataShared
import com.mironov.newsapp.repository.retrofit.NewsApi
import com.mironov.newsapp.ui.MainActivity
import com.mironov.newsapp.ui.MainViewModel
import com.mironov.newsapp.ui.screens.NewsListFragment
import dagger.*
import dagger.multibindings.IntoMap

@Component(modules = [AppModule::class,RetrofitModule::class,AppBindsModule::class])

interface AppComponent  {
    fun inject(activity: MainActivity)
    fun inject(newsListFragment: NewsListFragment)

    val factory: MultiViewModelFactory

    @Component.Builder
    interface Builder {

        fun build(): AppComponent

        @BindsInstance
        fun context(context: Context): Builder
    }
}

@Module
class AppModule() {
    @Provides
    fun provideRepository(dataShared: DataShared, retrofit: NewsApi): Repository {
        return Repository(dataShared, retrofit)
    }

    @Provides
    fun provideDataShared(context: Context): DataShared {
        return DataShared(context)
    }
}
@Module()
interface AppBindsModule {
    @Binds
    @[IntoMap ViewModelKey(MainViewModel::class)]
    fun provideMainViewModel(mainViewModel: MainViewModel): ViewModel
}






