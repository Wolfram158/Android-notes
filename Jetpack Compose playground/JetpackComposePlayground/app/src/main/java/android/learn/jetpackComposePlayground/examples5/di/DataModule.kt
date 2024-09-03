package android.learn.jetpackComposePlayground.examples5.di

import android.learn.jetpackComposePlayground.examples5.data.repository.AdditionRepositoryImpl
import android.learn.jetpackComposePlayground.examples5.data.repository.ListRepositoryImpl
import android.learn.jetpackComposePlayground.examples5.domain.repository.AdditionRepository
import android.learn.jetpackComposePlayground.examples5.domain.repository.ListRepository
import dagger.Binds
import dagger.Module

@Module
interface DataModule {
    @Binds
    fun bindAdditionRepository(impl: AdditionRepositoryImpl): AdditionRepository

    @Binds
    fun bindListRepository(impl: ListRepositoryImpl): ListRepository

}