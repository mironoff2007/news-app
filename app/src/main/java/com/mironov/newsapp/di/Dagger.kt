package com.mironov.newsapp.di

import android.content.Context
import androidx.lifecycle.ViewModel
import com.mironov.newsapp.repository.DataShared
import com.mironov.newsapp.repository.Repository
import com.mironov.newsapp.repository.retrofit.NewsApi
import com.mironov.newsapp.ui.MainActivity
import com.mironov.newsapp.ui.MainViewModel
import com.mironov.newsapp.ui.StartUpInfoFragmentViewModel
import com.mironov.newsapp.ui.screens.GreetingFragment
import com.mironov.newsapp.ui.screens.GuideFragment
import com.mironov.newsapp.ui.screens.NewsListFragment
import dagger.*
import dagger.multibindings.IntoMap

@Component(modules = [AppModule::class,RetrofitModule::class,AppBindsModule::class,GlideModule::class])

interface AppComponent  {
    fun inject(activity: MainActivity)
    fun inject(newsListFragment: NewsListFragment)
    fun inject(greetingFragment: GreetingFragment)
    fun inject(guideFragment: GuideFragment)

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

    @Binds
    @[IntoMap ViewModelKey(StartUpInfoFragmentViewModel::class)]
    fun provideStartUpInfoFragmentViewModel(startUpInfoFragmentViewModel: StartUpInfoFragmentViewModel): ViewModel
}






