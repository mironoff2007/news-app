package com.mironov.newsapp.di

import android.content.Context
import com.mironov.newsapp.MainActivity
import dagger.*

@Component(modules = [AppModule::class])

interface AppComponent  {
    fun inject(activity: MainActivity)

    @Component.Builder
    interface Builder {

        fun build(): AppComponent

        @BindsInstance
        fun context(context: Context): Builder
    }
}

@Module
class AppModule() {

}






