package android.learn.jetpackComposePlayground.examples5.di

import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.arkivanov.mvikotlin.main.store.DefaultStoreFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
interface PresentationModule {
    companion object {
        @Provides
        fun provideStoreFactory(): StoreFactory = DefaultStoreFactory()
    }
}