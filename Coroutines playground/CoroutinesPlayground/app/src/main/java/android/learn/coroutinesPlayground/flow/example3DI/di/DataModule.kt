package android.learn.coroutinesPlayground.flow.example3DI.di

import android.app.Application
import android.learn.coroutinesPlayground.flow.example3DI.data.database.AppDatabase
import android.learn.coroutinesPlayground.flow.example3DI.data.database.StringDao
import android.learn.coroutinesPlayground.flow.example3DI.data.repository.StringRepositoryImpl
import android.learn.coroutinesPlayground.flow.example3DI.domain.StringRepository
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
interface DataModule {
    @Binds
    fun bindStringRepository(impl: StringRepositoryImpl): StringRepository

    companion object {
        @Provides
        fun provideStringDao(application: Application): StringDao {
            return AppDatabase.getInstance(application).getStringDao()
        }

    }
}